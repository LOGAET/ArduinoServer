document.addEventListener('DOMContentLoaded', function () {
    const ctx = document.getElementById('temperatureChart').getContext('2d');
    let temperatureChart = new Chart(ctx, {
        type: 'line',
        data: {
            labels: [], // Начальные метки времени пустые
            datasets: [{
                label: 'Temperature',
                data: [], // Начальные данные пустые
                backgroundColor: 'rgba(3, 169, 244, 0.2)',
                borderColor: 'rgba(3, 169, 244, 1)',
                borderWidth: 2
            }]
        },
        options: {
            scales: {
                y: {
                    beginAtZero: false
                }
            }
        }
    });

    // Получаем ссылку на ползунок и метку
    const slider = document.getElementById('dataCount');
    const dataCountValue = document.getElementById('dataCountValue');

    // Обновление текста метки и запрос данных при изменении значения ползунка
    slider.oninput = function () {
        dataCountValue.textContent = this.value;
        fetchData();
    };

    // Функция для запроса данных
    function fetchData() {
        $.ajax({
            url: `/data?limit=${slider.value}`, // Используем значение ползунка для задания лимита
            method: 'GET',
            success: function (data) {
                //console.log(data);
                // Обновляем данные графика
                const labels = data.map(item => {
                    const date = new Date(item.id);
                    date.setTime(date.getTime() - 3 * 3600000);
                    return new Intl.DateTimeFormat('ru-RU', {
                        year: 'numeric', month: 'long', day: 'numeric',
                        hour: '2-digit', minute: '2-digit', second: '2-digit'
                    }).format(date);
                });

                const temperatures = data.map(item => item.temperature);

                // Ограничиваем данные графика значениями, выбранными ползунком
                temperatureChart.data.labels = labels.slice(-slider.value);
                temperatureChart.data.datasets.forEach((dataset) => {
                    dataset.data = temperatures.slice(-slider.value);
                });
                temperatureChart.update();
            },
            error: function (error) {
                console.error("Error fetching data: ", error);
            }
        });
    }

    // Запускаем fetchData каждые 5000 миллисекунд (5 секунд)
    setInterval(fetchData, 5000);
});