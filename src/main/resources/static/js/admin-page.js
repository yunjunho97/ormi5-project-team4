import {
    URL,
    MANAGE_POST,
    READ_POST,
    PAGE_ID,
    APPROVE_STATUS,
    ADOPTION_STATUS,
    API_ADMIN_GET_POSTS, PAGE_ID_PREVIOUS, PAGE_ID_NEXT, getPageStartNumber, getPageEndNumber
} from "./constant.js";

document.addEventListener("DOMContentLoaded", function() {
    let approveStatus = '';
    if(APPROVE_STATUS !== ''){
        approveStatus = `&approvestatus=${APPROVE_STATUS}`;
    }
    let adoptionStatus = '';
    if(ADOPTION_STATUS !== ''){
        adoptionStatus = `&adoptionstatus=${ADOPTION_STATUS}`
    }

    // url 설정
    const fetchURL = URL + API_ADMIN_GET_POSTS + `?&page=${PAGE_ID}` + approveStatus;
    const previousPageURL = URL + MANAGE_POST + `?&page=${PAGE_ID_PREVIOUS}` + approveStatus;
    const nextPageURL = URL + + MANAGE_POST + `?&page=${PAGE_ID_NEXT}` + approveStatus;

    // nav 필터링 관련
    const postingFilter = document.querySelector('#adoption-posting');
    const adoptedFilter = document.querySelector('#adoption-adopted');

    switch(APPROVE_STATUS){
        case 'POSTING':
            postingFilter.href = URL + MANAGE_POST + `?&page=${PAGE_ID}`;
            adoptedFilter.href = URL + MANAGE_POST + `?&page=${PAGE_ID}&approvestatus=ADOPTED`;
            break;
        case 'ADOPTED':
            postingFilter.href = URL + MANAGE_POST + `?&page=${PAGE_ID}&approvestatus=POSTING`;
            adoptedFilter.href = URL + MANAGE_POST + `?&page=${PAGE_ID}`;
            break;
        default:
            postingFilter.href = URL + MANAGE_POST + `?&page=${PAGE_ID}&approvestatus=POSTING`;
            adoptedFilter.href = URL + MANAGE_POST + `?&page=${PAGE_ID}&approvestatus=ADOPTED`;
            break;
    }

    // 유저 정보 받아오기
    fetch(fetchURL)
        .then(response => response.json())
        .then(data => {
            // todo: 로그인 정보 연동
        })
        .catch(error => console.error('Error:', error));

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
                input.className = 'checkbox';
                const img = document.createElement('img');
                // todo : db연결
                img.src = '/images/animal-test-img.svg'
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
            const itemCount = data.totalElements;
            const numOfLists = data.totalPages;
            const itemList = document.getElementById('post-pages');

            // 이전 페이지 바로가기 이미지
            const previousLi = document.createElement('li');
            const goPreviousPage = document.createElement('a');
            const previousPageImg = document.createElement('img');
            previousPageImg.src = '/images/page-previous.svg';
            previousPageImg.alt = '이전 페이지'
            if (!(PAGE_ID === 0)) {
                goPreviousPage.href = previousPageURL;
            }

            goPreviousPage.appendChild(previousPageImg);
            previousLi.appendChild(goPreviousPage);
            itemList.appendChild(goPreviousPage);

            // 페이지 생성
            for (let i = getPageStartNumber(PAGE_ID, numOfLists); i <= getPageEndNumber(PAGE_ID, numOfLists); i++) {
                const li = document.createElement('li');
                const pageElement = document.createElement(
                    PAGE_ID === i ? 'p' : 'a'
                )
                pageElement.textContent = i.toString();
                if (pageElement.tagName.toLowerCase() === 'p') {
                    pageElement.className = 'font-page-selected';
                } else {
                    pageElement.className = 'font-page';
                    pageElement.href = URL + MANAGE_POST + `?&page=${i}` + approveStatus;
                }

                li.appendChild(pageElement);
                itemList.appendChild(li);
            }

            // 다음 페이지 바로가기 이미지
            const nextLi = document.createElement('li');
            const goNextPage = document.createElement('a');
            const nextPageImg = document.createElement('img');
            nextPageImg.src = '/images/page-next.svg';
            nextPageImg.alt = '다음 페이지'
            if (!(PAGE_ID === numOfLists - 1)) {
                goNextPage.href = nextPageURL;
            }

            goNextPage.appendChild(nextPageImg);
            nextLi.appendChild(goNextPage);
            itemList.appendChild(goNextPage);
        })
        .catch(error => console.error('Error:', error));
});