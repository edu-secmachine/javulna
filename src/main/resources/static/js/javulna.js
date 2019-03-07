(function (javulna) {

    javulna(window.jQuery, window, document);

}(function ($, window, document) {

    $(function () {

        $('.clickableDesc').click(function (ev) {
            ev.preventDefault();
            let link = ev.target.href;
            let id = link.substr(link.indexOf('#') + 1, link.length)
            $.get('/rest/movie/' + id, function (data, success) {
                if (success) {
                    let url = '#'+data.id;
                    $(".clickableDesc[href='" + url + "']").html(data.description);
                    console.log(data.description);

                }
            });
        });


    });



}));