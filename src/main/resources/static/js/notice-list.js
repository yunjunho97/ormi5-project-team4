import {
    URL,
    PAGE_ID,
    API_NOTICE,
    NOTICE_LIST, READ_NOTICE
} from "./constant.js";

import {
    getDateFormat,
    calculatePagination
} from "./utils.js";

document.addEventListener("DOMContentLoaded", function () {
    // url 설정
    const fetchURL = URL + API_NOTICE + `?page=${PAGE_ID}`;

    // 공지 목록 받아오기
    fetch(fetchURL)
        .then(response => response.json())
        .then(data => {
            const tbody = document.querySelector('#tbody-user-list');
            data.content.forEach(notice => {
                const tr = document.createElement('tr');
                tr.className = 'height-35 table-line-bottom';
                const category = document.createElement('td');
                category.className = 'notice-category';
                category.textContent = '[ 공지사항 ]';
                const titleTd = document.createElement('td');
                const title = document.createElement('a');
                title.textContent = notice.title;
                title.href = URL + READ_NOTICE + '/' + notice.id;
                const createdAt = document.createElement('td');
                createdAt.className = 'notice-created-at text-align-center';
                createdAt.textContent = getDateFormat(notice.createdAt);

                titleTd.appendChild(title);

                tr.appendChild(category);
                tr.appendChild(titleTd);
                tr.appendChild(createdAt);

                tbody.appendChild(tr);
            });

            // 게시글 페이지 개수 생성
            const numOfLists = data.totalPages;
            const itemList = document.getElementById('notice-pages');

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
                    pageElement.href = URL + NOTICE_LIST + `?&page=${i}`;
                }

                li.appendChild(pageElement);
                itemList.appendChild(li);
            }
        })
        .catch(error => console.error('Error:', error));
});