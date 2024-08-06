import {
    URL,
    PAGE_ID,
    MY_INFO,
    ADMIN_USER,
    WRITE_NOTICE,
    API_ADMIN_NOTICE,
    ADMIN_NOTICE
} from "./constant.js";

import {
    getResponseForUserRole,
    getDateFormat,
    calculatePagination, getResponseForUserRoleAdmin
} from "./utils.js";

document.addEventListener("DOMContentLoaded", function () {
    // url 설정
    const fetchURL = URL + API_ADMIN_NOTICE + `?page=${PAGE_ID}`;
    const previousPageURL = URL + ADMIN_NOTICE + `?page=${PAGE_ID}`;
    const nextPageURL = URL + ADMIN_NOTICE + `?page=${PAGE_ID}`;

    // 유저 정보 데이터 세팅
    MY_INFO.then(info => {
        const username = document.querySelector('#board-username');
        username.textContent = info.userName;
        const role = document.querySelector('#board-user-role');
        role.textContent = getResponseForUserRole(info.role)
        const registerDate = document.querySelector('#board-user-createdAt');
        registerDate.textContent = getDateFormat(info.createdAt);
        const registerDescription = document.querySelector('#board-user-created-description');
        registerDescription.innerHTML = '&nbsp;가입';
        const userPhone = document.querySelector('#board-user-phone');
        userPhone.textContent = info.phone;
        const userEmail = document.querySelector('#board-user-email');
        userEmail.textContent = info.email;
    }).catch(error => console.error('Error:', error));

    // 공지 목록 받아오기
    fetch(fetchURL)
        .then(response => response.json())
        .then(data => {
            const tbody = document.querySelector('#tbody-user-list');
            data.content.forEach(notice => {
                const tr = document.createElement('tr');
                tr.className = 'height-35 table-line-bottom';
                const checkBoxTd = document.createElement('td');
                const checkBox = document.createElement('input');
                checkBox.className = 'checkbox-all item-checkbox';
                checkBox.type = 'checkbox'
                checkBox.id = notice.id;
                const category = document.createElement('td');
                category.className = 'notice-category';
                category.textContent = '[ 공지사항 ]';
                const title = document.createElement('td');
                title.textContent = notice.title;
                const createdAt = document.createElement('td');
                createdAt.className = 'notice-created-at text-align-center';
                createdAt.textContent = getDateFormat(notice.createdAt);

                checkBoxTd.appendChild(checkBox);

                tr.appendChild(checkBoxTd);
                tr.appendChild(category);
                tr.appendChild(title);
                tr.appendChild(createdAt);

                tbody.appendChild(tr);
            });

            // 게시글 페이지 개수 생성
            const numOfLists = data.totalPages;
            const itemList = document.getElementById('notice-pages');

            // // 이전 페이지 바로가기 이미지
            // const previousLi = document.createElement('li');
            // const goPreviousPage = document.createElement('a');
            // const previousPageImg = document.createElement('img');
            // previousPageImg.src = '/images/page-previous.svg';
            // previousPageImg.alt = '이전 페이지'
            // if (!(PAGE_ID === 0)) {
            //     goPreviousPage.href = previousPageURL;
            // }
            //
            // goPreviousPage.appendChild(previousPageImg);
            // previousLi.appendChild(goPreviousPage);
            // itemList.appendChild(goPreviousPage);

            const pagination = calculatePagination(numOfLists, PAGE_ID);

            // 페이지 생성
            for (let i = pagination.startPage; i <= pagination.endPage; i++) {
                const li = document.createElement('li');
                const pageElement = document.createElement(
                    PAGE_ID === i ? 'p' : 'a'
                )
                pageElement.textContent = (i + 1).toString();
                if (pageElement.tagName.toLowerCase() === 'p') {
                    pageElement.className = 'font-page-selected';
                } else {
                    pageElement.className = 'font-page';
                    pageElement.href = URL + ADMIN_USER + `?&page=${i}` + email;
                }

                li.appendChild(pageElement);
                itemList.appendChild(li);
            }

            // // 다음 페이지 바로가기 이미지
            // const nextLi = document.createElement('li');
            // const goNextPage = document.createElement('a');
            // const nextPageImg = document.createElement('img');
            // nextPageImg.src = '/images/page-next.svg';
            // nextPageImg.alt = '다음 페이지'
            // if (!(PAGE_ID === numOfLists - 1)) {
            //     goNextPage.href = nextPageURL;
            // }
            //
            // goNextPage.appendChild(nextPageImg);
            // nextLi.appendChild(goNextPage);
            // itemList.appendChild(goNextPage);
        })
        .catch(error => console.error('Error:', error));

    // 전체 선택 이벤트 리스너
    const selectAllCheckbox = document.querySelector('#check-all-notice');
    if (selectAllCheckbox) {
        selectAllCheckbox.addEventListener('click', function () {
            toggleAllCheckboxes(this);
        });
    }

    // 공지 글쓰기 버튼 이벤트 리스너
    const writeNoticeButton = document.querySelector('#notice-write');
    if (writeNoticeButton) {
        writeNoticeButton.addEventListener('click', function () {
            writeNotice();
        });
    }

    // 공지 삭제 버튼 이벤트 리스너
    const deleteNoticeButton = document.querySelector('#notice-delete');
    if (deleteNoticeButton) {
        deleteNoticeButton.addEventListener('click', function () {
            deleteNotice();
        });
    }
});

function toggleAllCheckboxes(source) {
    const checkboxes = document.querySelectorAll('.item-checkbox');
    checkboxes.forEach(checkbox => checkbox.checked = source.checked);
}

function writeNotice() {
    window.location.href = URL + WRITE_NOTICE;
}

function deleteNotice() {
    const notices = document.querySelectorAll('.item-checkbox');
    notices.forEach(notice => {
        if (notice.checked) {
            fetch(URL + API_ADMIN_NOTICE + '/' + notice.id, {
                method: 'DELETE'
            }).catch(error => console.error('Error:', error));
        }
    });

    alert(`요청을 성공적으로 수행했습니다.`);
}