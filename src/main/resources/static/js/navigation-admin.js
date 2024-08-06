import {
    URL,
    API_NOTICE,
    HOME,
    ADMIN_PAGE,
    ADMIN_NOTICE, ADMIN_USER, NOTICE_LIST
} from './constant.js'

import {
    setNavigationFilter, setNavigationCategoryStyle, setAdminNavigationInfo
} from './utils.js';

document.addEventListener("DOMContentLoaded", function () {
    const nav = document.querySelector('#nav-admin');
    const ul = document.createElement('ul');

    fetch(URL + API_NOTICE)
        .then(response => response.json())
        .then(data => {
            if (Array.isArray(data)) {
                const adminHead = document.createElement('li');
                const adminTitle = document.createElement('a');
                adminTitle.href = URL + ADMIN_PAGE;
                adminTitle.className = 'font-nav-category-selected container-nav-category-selected';
                adminTitle.textContent = '관리자 페이지';
                const adminContentsArea = document.createElement('div');
                const adminContentsList = document.createElement('ul');

                const manageNoticeLi = document.createElement('li');
                manageNoticeLi.className = 'line-nav';
                const manageNoticeTitle = document.createElement('a');
                manageNoticeTitle.href = URL + ADMIN_NOTICE;
                manageNoticeTitle.className = 'font-nav-title';
                manageNoticeTitle.textContent = '공지 관리';
                const manageNoticeDescription = document.createElement('a');
                manageNoticeDescription.href = URL + ADMIN_NOTICE;
                manageNoticeDescription.textContent = '공지 관리 페이지';
                manageNoticeLi.appendChild(manageNoticeTitle);
                manageNoticeLi.appendChild(manageNoticeDescription);

                const managePostLi = document.createElement('li');
                managePostLi.className = 'line-nav';
                const managePostTitle = document.createElement('a');
                managePostTitle.href = URL + ADMIN_PAGE;
                managePostTitle.className = 'font-nav-title';
                managePostTitle.textContent = '게시물 관리';
                const managePostDescription = document.createElement('a');
                managePostDescription.href = URL + ADMIN_PAGE;
                managePostDescription.textContent = '게시물 관리 페이지';
                managePostLi.appendChild(managePostTitle);
                managePostLi.appendChild(managePostDescription);

                const manageUserLi = document.createElement('li');
                const manageUserTitle = document.createElement('a');
                manageUserTitle.href = URL + ADMIN_USER;
                manageUserTitle.className = 'font-nav-title';
                manageUserTitle.textContent = '사용자 관리';
                const manageUserDescription = document.createElement('a');
                manageUserDescription.href = URL + ADMIN_USER;
                manageUserDescription.textContent = '사용자 관리 페이지';
                manageUserLi.appendChild(manageUserTitle);
                manageUserLi.appendChild(manageUserDescription);

                const pendingFilter = document.createElement('li');
                pendingFilter.className = 'container-nav-category-selected';
                const pendingPortal = document.createElement('a');
                pendingPortal.href = URL + NOTICE_LIST;
                pendingPortal.textContent = '대기중인 공고';
                pendingFilter.appendChild(pendingPortal);

                const approvedFilter = document.createElement('li');
                const approvedPortal = document.createElement('a');
                approvedPortal.href = URL + HOME; // todo: 게시글 상태 필터링
                approvedPortal.textContent = '승인된 공고';
                approvedFilter.appendChild(approvedPortal);

                const deniedFilter = document.createElement('li');
                const deniedPortal = document.createElement('a');
                deniedPortal.textContent = '거절된 공고';
                deniedFilter.appendChild(deniedPortal);

                setAdminNavigationInfo(
                    pendingFilter, pendingPortal,
                    approvedFilter, approvedPortal,
                    deniedFilter, deniedPortal);

                adminContentsList.appendChild(manageNoticeLi);
                adminContentsList.appendChild(managePostLi);
                adminContentsList.appendChild(manageUserLi);

                adminContentsArea.appendChild(adminContentsList);

                adminHead.appendChild(adminTitle);
                adminHead.appendChild(adminContentsArea);


                ul.appendChild(adminHead);
                ul.appendChild(pendingFilter)
                ul.appendChild(approvedFilter);
                ul.appendChild(deniedFilter);

                nav.appendChild(ul);
            } else {
                console.error('Error:', data);
            }
        })
        .catch(error => console.error('Error:', error));

});

function truncateText(text, maxLength) {
    if (text.length > maxLength) {
        return text.slice(0, maxLength) + '..';
    }
    return text;
}