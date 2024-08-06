document.addEventListener('DOMContentLoaded', function() {
    // 사용자 정보를 가져와서 화면에 표시
    fetchUserInfo();

    // 글쓰기 버튼 클릭
    const writeButton = document.getElementById('write-button');
    writeButton.addEventListener('click', function() {
        console.log('글쓰기 버튼 클릭됨');
        // 글쓰기 페이지로 이동
        window.location.href = '/write';
    });

    // 삭제 버튼 클릭
    const deleteButton = document.getElementById('delete-button');
    deleteButton.addEventListener('click', function() {
        console.log('삭제 버튼 클릭됨');
        // 선택된 항목 삭제
        const checkboxes = document.querySelectorAll('.checkbox-all');
        checkboxes.forEach(checkbox => {
            if (checkbox.checked) {
                checkbox.closest('tr').remove();
            }
        });
    });

    // 사용자 정보 가져오기
    function fetchUserInfo() {
        fetch('/mypage/this/info') // Spring Boot에서 제공하는 API endpoint
            .then(response => response.json())
            .then(data => {
                // 사용자 정보를 화면에 표시
                document.getElementById('board-username').textContent = data.username;
                document.getElementById('board-user-createdAt').textContent = data.createdAt;
                document.getElementById('board-user-phone').textContent = data.phone;
                document.getElementById('board-user-email').textContent = data.email;
                document.getElementById('user-posting-count').textContent = data.postCount;

                // 글 목록 가져오기
                fetchUserPosts();
            })
            .catch(error => {
                console.error('Error fetching user info:', error);
            });
    }

    // 사용자가 작성한 글 목록 가져오기
    function fetchUserPosts() {
        fetch('/mypage/this/post')
            .then(response => response.json())
            .then(posts => {
                const postListBody = document.getElementById('post-list-body');
                posts.forEach(post => {
                    const row = document.createElement('tr');
                    row.classList.add('height-35', 'my-page-table-line-bottom');
                    row.innerHTML = `
                        <td><input type="checkbox" class="checkbox-all"></td>
                        <td class="post-posting">${post.status}</td>
                        <td>${post.title}</td>
                        <td class="post-created">${post.created}</td>
                    `;
                    postListBody.appendChild(row);
                });
            })
            .catch(error => {
                console.error('Error fetching user posts:', error);
            });
    }
});