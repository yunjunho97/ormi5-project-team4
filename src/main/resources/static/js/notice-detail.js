import {
    URL,
    NOTICE_LIST, API_NOTICE
} from './constant.js'
import {getDateTimeformat, getLastPathVariable} from "./utils.js";

document.addEventListener("DOMContentLoaded", function () {
    fetch(URL + API_NOTICE + '/' + getLastPathVariable())
        .then(response => response.json())
        .then(data => {
            console.log(data);
            const title = document.querySelector('#notice-detail-title');
            title.textContent = data.title;
            const createdAt = document.querySelector('#notice-detail-created-at');
            createdAt.textContent = getDateTimeformat(data.createdAt);
            const content = document.querySelector('#textarea-notice');
            content.className = 'margin-bottom-19'
            content.value = data.content;
            content.disabled = true;
        })
        .catch(error => console.error('Error:', error));

    // 공지 글쓰기 버튼 이벤트 리스너
    const noticeListPortalButton = document.querySelector('#button-notice-list-portal');
    if (noticeListPortalButton) {
        noticeListPortalButton.addEventListener('click', function () {
            window.location.href = URL + NOTICE_LIST;
        });
    }
});