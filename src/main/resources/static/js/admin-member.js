import {
    URL,
    PAGE_ID,
    MY_INFO, EMAIL, API_ADMIN_GET_USERS, ADMIN_USER, API_ADMIN_PUT_USER_ROLE
} from "./constant.js";

import {
    getResponseForUserRole,
    getDateFormat,
    calculatePagination, getResponseForUserRoleAdmin
} from "./utils.js";

document.addEventListener("DOMContentLoaded", function () {
    let email = '';
    if (EMAIL !== '') {
        email = `&email=${EMAIL}`;
    }

    // url 설정
    const fetchURL = URL + API_ADMIN_GET_USERS + `?page=${PAGE_ID}` + email;
    const previousPageURL = URL + ADMIN_USER + `?page=${PAGE_ID}` + email;
    const nextPageURL = URL + ADMIN_USER + `?page=${PAGE_ID}` + email;

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

    console.log(fetchURL);
    // 유저 목록 받아오기
    fetch(fetchURL)
        .then(response => response.json())
        .then(data => {
            const tbody = document.querySelector('#tbody-user-list');
            data.content.forEach(user => {
                const tr = document.createElement('tr');
                tr.className = 'height-35 table-line-bottom';
                const checkBoxTd = document.createElement('td');
                const checkBox = document.createElement('input');
                checkBox.className = 'checkbox-all item-checkbox';
                checkBox.type = 'checkbox'
                checkBox.id = user.id;
                const userName = document.createElement('td');
                userName.className = 'member-username';
                userName.textContent = user.userName;
                const userEmail = document.createElement('td');
                userEmail.textContent = user.email;
                const userPhone = document.createElement('td');
                userPhone.className = 'member-phone';
                userPhone.textContent = user.phone;
                const userRoleSetData = getResponseForUserRoleAdmin(user.role);
                const userRole = document.createElement('td');
                userRole.className = userRoleSetData.className.toString();
                userRole.textContent = userRoleSetData.text;

                checkBoxTd.appendChild(checkBox);

                tr.appendChild(checkBoxTd);
                tr.appendChild(userName);
                tr.appendChild(userEmail);
                tr.appendChild(userPhone);
                tr.appendChild(userRole);

                tbody.appendChild(tr);
            });

            // 게시글 페이지 개수 생성
            const numOfLists = data.totalPages;
            const itemList = document.getElementById('member-pages');

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
    const selectAllCheckbox = document.querySelector('#check-all-member');
    if (selectAllCheckbox) {
        selectAllCheckbox.addEventListener('click', function () {
            toggleAllCheckboxes(this);
        });
    }

    // 게시글 상태 변경 버튼 이벤트 리스너
    const setUserBannedButton = document.querySelector('#user-banned');
    if (setUserBannedButton) {
        setUserBannedButton.addEventListener('click', function () {
            processSelectedItems('INACTIVE');
        });
    }
    const setUserActiveButton = document.querySelector('#user-active');
    if (setUserActiveButton) {
        setUserActiveButton.addEventListener('click', function () {
            processSelectedItems('ACTIVE');
        });
    }

    // 검색 이벤트 리스너
    const form = document.querySelector('#search-form');

    form.addEventListener('submit', function (event) {
        event.preventDefault();
        const searchEmail = document.querySelector('#search-input').value;
        window.location.href = URL + ADMIN_USER + `?page=0` + '&email=' + searchEmail;
    });
});

function toggleAllCheckboxes(source) {
    const checkboxes = document.querySelectorAll('.item-checkbox');
    checkboxes.forEach(checkbox => checkbox.checked = source.checked);
}

function processSelectedItems(mode) {
    const users = document.querySelectorAll('.item-checkbox');
    users.forEach(user => {
        if (user.checked) {
            fetch(URL + API_ADMIN_PUT_USER_ROLE + '/' + user.id + '?role=' + mode, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json',
                }
            })
                .then(response => response.json())
                .then(data => console.log('Success:', data))
                .catch(error => console.error('Error:', error));
        }
    });

    alert(`요청을 성공적으로 수행했습니다.`);
}