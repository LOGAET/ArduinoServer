$(document).ready(function () {
    var table = $('#temperatureTable').DataTable();

    function updateTableData() {
        $.ajax({
            url: '/data?limit=100', // Предположим, что это URL для запроса новых данных
            method: 'GET',
            success: function (data) {
                table.clear(); // Очищаем таблицу
                data.forEach(function (item) {
                    table.row.add([
                        new Intl.DateTimeFormat('ru-RU', {
                            year: 'numeric', month: 'long', day: 'numeric',
                            hour: '2-digit', minute: '2-digit', second: '2-digit'
                        }).format(new Date(item.id)), // Форматируем дату
                        item.temperature // Температура
                    ]);
                });
                table.draw(); // Перерисовываем таблицу с новыми данными
            },
            error: function (error) {
                console.error("Error fetching data: ", error);
            }
        });
    }

    // Устанавливаем интервал обновления данных таблицы
    setInterval(updateTableData, 5000); // Обновляем каждые 5 минут

    // Начальный запрос данных для графика и таблицы
    updateTableData();
});

    $(document).ready( function () {
    $('#temperatureTable').DataTable();
} );