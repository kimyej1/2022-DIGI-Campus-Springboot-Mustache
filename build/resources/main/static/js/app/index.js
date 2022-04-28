// index.js

var main = {
    init : function() {
        var _this = this;
        $('#btn-write').on('click', function() {  // id가 btn-write인 녀석을 찾아서 click될 때 function을 수행해
            _this.save();
        });
        $('#btn-update').on('click', function() {
            _this.update();
        });
        $('#btn-delete').on('click', function() {
            _this.delete();
        });
    },
    save : function() {
        var data = {
            title: $('#title').val(), // val(): 값을 가져와, val(xxx): xxx로 값을 세팅해
            content: $('#content').val(),
            author: $('#author').val()
        };

        $.ajax({
            type: 'POST',
            dataType: 'json',
            url: '/api/v1/posts',    // localhost:8080/api/v1/posts
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function(returnData){    // done(다되면 : 200이 오면) function을 수행해
            alert('글이 등록되었습니다.');
            $('#debug').html(returnData);   // 혹시 받아오는 데이터가 있으면 디버그 영역에 보여줘
            location.href='/';
        }).fail(function(error){    // fail(실패하면 : 200 외 응답이 오면) function을 수행해
            alert(error);
            $('#debug').html(error);
            alert(JSON.stringify(error));
        })
    },
    update : function() {
        let id = $("#id").val();
        //alert('수정할 글 번호 = ' + id);
        var data = {
            title: $('#title').val(),
            content: $('#content').val()
        };

        $.ajax({
            type: 'PUT',
            dataType: 'json',
            url: '/api/v1/posts/' + id,    // localhost:8080/api/v1/posts/3
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        })
        .done(function(){
            alert('변경되었습니다.');
            location.href='/';
        })
        .fail(function(error){
            alert(error);
        });
    },
    delete : function() {
        let id = $("#id").val();
        //alert('삭제할 글 번호 : '+ id);

        if(confirm('정말 삭제하시겠습니까?'))
        {
        // method chain : $.ajax().done().fail();
            $.ajax({
                type: 'DELETE',
                url: '/api/v1/posts/' + id,
                dataType: 'json',
                contentType: 'application/json; charset=utf-8'
            })
            .done(function(){
                alert('삭제되었습니다.');
                location.href='/';
            })
            .fail(function(error){
                alert(error);
            });
        }else
        {
            // 취소..
        }

    }

}

main.init();