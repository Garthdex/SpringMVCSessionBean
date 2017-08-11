$(document).ready(function () {
    $('#newBookForm').submit(function (event) {
        var title = $('#bookTitle').val();
        var author = $('#bookAuthor').val();
        var json = {"bookTitle" : title, "bookAuthor" : author};

        $.ajax({
            url: '/books/add/ajax',
            data: JSON.stringify(json),
            type: "POST",
            dataType: 'json',
            beforeSend: function(xhr) {
                xhr.setRequestHeader("Accept", "application/json");
                xhr.setRequestHeader("Content-Type", "application/json");
            },
            success: function(response) { success(response) },
            error: function(e) {
                alert('smth wrong');
            }
        });

        event.preventDefault();
    });

    function success(book) {
        $("#showAjax").html(json);
        var respContent = "";

        respContent += "<span class='success'>Book was added: [";
        respContent += book.title + " : ";
        respContent += book.author + "]</span>";

        $("#addBookFromResponse").html(respContent);
    }
});