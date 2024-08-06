import {
    URL,
    MANAGE_POST,
    READ_POST,
    PAGE_ID,
    APPROVE_STATUS,
    ADOPTION_STATUS,
    API_ADMIN_GET_POSTS, MY_INFO, API_SET_APPROVE_STATUS
} from "./constant.js";

import {
    getResponseForAdoptionStatus,
    getImgSrc,
    getResponseForUserRole,
    getDateFormat,
    calculatePagination
} from "./utils.js";

document.addEventListener("DOMContentLoaded", function () {
    let approveStatus = '';
    if (APPROVE_STATUS !== '') {
        approveStatus = `&approveStatus=${APPROVE_STATUS}`;
    }
    let adoptionStatus = '';
    if (ADOPTION_STATUS !== '') {
        adoptionStatus = `&adoptionStatus=${ADOPTION_STATUS}`
    }

    // url 설정
    const fetchURL = URL + API_ADMIN_GET_POSTS + `?page=${PAGE_ID}` + approveStatus + adoptionStatus;
    const previousPageURL = URL + MANAGE_POST + `?page=${PAGE_ID}` + approveStatus + adoptionStatus;
    const nextPageURL = URL + +MANAGE_POST + `?page=${PAGE_ID}` + approveStatus + adoptionStatus;

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

    // 게시글 받아오기
    fetch(fetchURL)
        .then(response => response.json())
        .then(data => {
            const postList = document.getElementById('post-list');
            data.content.forEach(post => {
                const listItem = document.createElement('li');
                listItem.className = 'container-post-img'
                const div = document.createElement('div');
                div.className = 'image-container'
                const input = document.createElement('input');
                input.type = 'checkbox';
                input.className = 'checkbox item-checkbox';
                input.id = post.id;
                const img = document.createElement('img');
                img.src = getImgSrc(post);
                img.alt = '이미지';
                img.className = 'margin-bottom-7'
                const status = document.createElement("p");
                status.textContent = getResponseForAdoptionStatus(post.adoptionStatus);
                status.className = 'font-forward margin-bottom-3'
                const title = document.createElement('a');
                title.href = URL + READ_POST + `${post.id}`;
                title.textContent = post.title;

                div.appendChild(input);
                div.appendChild(img);
                listItem.appendChild(div);
                listItem.appendChild(status);
                listItem.appendChild(title);

                postList.appendChild(listItem);
            });

            // 게시글 페이지 개수 생성
            const numOfLists = data.totalPages;
            const itemList = document.getElementById('post-pages');

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
                    pageElement.href = URL + MANAGE_POST + `?&page=${i}` + approveStatus;
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
    const selectAllCheckbox = document.querySelector('#check-all');
    if (selectAllCheckbox) {
        selectAllCheckbox.addEventListener('click', function () {
            toggleAllCheckboxes(this);
        });
    }

    // 게시글 상태 변경 버튼 이벤트 리스너
    const setPostApprovedButton = document.querySelector('#post-approved');
    if (setPostApprovedButton) {
        setPostApprovedButton.addEventListener('click', function () {
            processSelectedItems('APPROVED');
        });
    }
    const setPostDeniedButton = document.querySelector('#post-denied');
    if (setPostDeniedButton) {
        setPostDeniedButton.addEventListener('click', function () {
            processSelectedItems('DENIED');
        });
    }
});

function toggleAllCheckboxes(source) {
    const checkboxes = document.querySelectorAll('.item-checkbox');
    checkboxes.forEach(checkbox => checkbox.checked = source.checked);
}

function processSelectedItems(mode) {
    let successCount = 0;
    let failCount = 0;
    const posts = document.querySelectorAll('.item-checkbox');
    posts.forEach(post=>{
        if(post.checked){
            fetch(URL + API_SET_APPROVE_STATUS + '/' + post.id + '?approveStatus=' + mode, {
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