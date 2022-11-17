var main = {
    init : function () {
        var _this = this;
        $('#btn-save').on('click', function () {
            _this.save();
        });

<<<<<<< HEAD
        $('#btn-update').on('click', function () {
            _this.update();
        });

        $('#btn-delete').on('click', function () {
            _this.delete();
        });
=======
        $('#btn-update').on('click', function() {
            _this.update();
        });

        $('#btn-delete').on('click', function() {
            _this.delete();
        });

>>>>>>> origin/main
    },
    save : function () {
        var data = {
            title: $('#title').val(),
            author: $('#author').val(),
            content: $('#content').val()
        };

        $.ajax({
            type: 'POST',
            url: '/api/v1/posts',
            dataType: 'json',
<<<<<<< HEAD
            contentType:'application/json; charset=utf-8',
=======
            contentType: 'application/json; charset=utf-8',
>>>>>>> origin/main
            data: JSON.stringify(data)
        }).done(function() {
            alert('글이 등록되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },

    update : function () {
        var data = {
            title: $('#title').val(),
            content: $('#content').val()
        };

        var id = $('#id').val();

        $.ajax({
            type: 'PUT',
            url: '/api/v1/posts/'+id,
            dataType: 'json',
<<<<<<< HEAD
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('글이 수정되었습니다.');
            window.location.href = '/';
=======
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function () {
            alert('글이 수정되었습니다.');
            window.location.href='/';
>>>>>>> origin/main
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },

    delete : function () {
        var id = $('#id').val();

        $.ajax({
            type: 'DELETE',
<<<<<<< HEAD
            url: '/api/v1/posts/'+id,
            dataType: 'json',
            contentType:'application/json; charset=utf-8'
        }).done(function() {
            alert('글이 삭제되었습니다.');
            window.location.href = '/';
=======
            url: '/api/v1/posts/' +id,
            dataType: 'json',
            contentType: 'application/json; charset=utf-8'
        }).done(function () {
            alert('글이 삭제되엇습니다.');
            window.location.href='/';
>>>>>>> origin/main
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    }
};

<<<<<<< HEAD
main.init();
=======
main.init();

/* 설명 */
/* window.location.href='/'; : 글 등록이 성공하면 "/"로 이동 */

/* index.js에서 var main={} 이라는 코드를 쓰는 이유? */
/* 여러 사람이 참여하는 프로젝트에서는 중복된 함수 이름이 자주 발생할 수 있는데, 이런 문제를 피하려고 유효 범위(scope)를 만들어 사용 */
/* 이렇게 하면 index 객체 안에서만 function이 유효하기 때문에 다른 js와 겹칠 위험이 사라진다. */



/* $('#btn-update').on('click') */
/* btn-update란 id를 가진 HTML 엘리먼트에 click 이벤트가 발생할 때 update function을 실행하도록 이벤트를 등록 */
>>>>>>> origin/main
