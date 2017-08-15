$(document).ready(function () {
    getBooks();
    var json = '';
    var body = $('body');
    body.on('click', '.jsDeleteButton', function () {
        deleteBook($(this).val());
    });

    $('#newBookForm').submit(function (event) {
        var title = $('#bookTitle').val();
        var author = $('#bookAuthor').val();
        json = {"bookTitle" : title, "bookAuthor" : author};

        $.ajax({
            url: window.location.origin + '/books/add/ajax',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(json),
            type: "POST",
            dataType: 'json',
            async: true,
            success: function(s) {
                successMessage(s)
                getBooks();
            },
            error: function(e) {

                alert(e.responseText);
            }
        });

        event.preventDefault();
    });

    function successMessage (book) {
        $(".showAjax").html(json);
        var respContent = "";

        respContent += "<span class='success'>Book was added: [";
        respContent += book.bookTitle + " : ";
        respContent += book.bookAuthor + "]</span>";

        $(".addBookFromResponse").html(respContent);
    }

    function getBooks() {
        $.ajax({
            type: "GET",
            url: window.location.origin + '/books/ajax',
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            cache: false,
            success: function (s) { fillTable(s)  },

            error: function (e) {

                alert(e.responseText);
            }
        })
    }

    function fillTable(items) {
        var trHTML = '<table>\n' +
            '        <tbody>\n' +
            '        <tr>\n' +
            '            <th width="80">ID</th>\n' +
            '            <th width="120">Title</th>\n' +
            '            <th width="120">Author</th>\n' +
            '            <th width="60">Edit</th>\n' +
            '            <th width="60">Delete</th>\n' +
            '        </tr>';

        items.forEach( function (item) {

            trHTML += '<tr>' +
                '<td>' + item.id + '</td>' +
                '<td>' + item.bookTitle + '</td>' +
                '<td>' + item.bookAuthor + '</td>' +
                '<td>' + '<button class="jsEditButton" type="button" value="' + item.id + '">' + 'Edit' + '</button></td>' +
                '<td>' + '<button class="jsDeleteButton" type="button" value="' + item.id + '">' + 'Delete' + '</button></td>' +
                '</tr>';
        });

        trHTML += '</tbody>\n' +
            '           </table>';
        $('.fromAjax').html(trHTML);

    }
    
    function deleteBook(id) {

        $.ajax({
            type: "DELETE",
            url: window.location.origin + '/remove/ajax/' + id,
            contentType: 'application/json; charset=utf-8',
            dataType: 'json',
            async: true,
            success: function (s) {
                successDeleteMessage(s);
                getBooks();
            },
            error: function (e) {

                alert(e.responseText);
            }
        })
    }

    function successDeleteMessage (book) {
        $(".showAjax").html(json);
        var respContent = "";

        respContent += "<span class='success'>Book was deleted: [";
        respContent += book.bookTitle + " : ";
        respContent += book.bookAuthor + "]</span>";

        $(".addBookFromResponse").html(respContent);
    }

});