$(function () {
    $("#searchtext").typeahead({
        source: function (query, process) {
            return $.get('/rest/search?query=' + query, function (data) {
                return process(_.map(data, function(item){return item.title;}));
            });
        }

    });
});