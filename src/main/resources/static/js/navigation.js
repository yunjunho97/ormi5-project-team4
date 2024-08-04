import {URL, API_NOTICE, HOME, NOTICE_LIST} from './constant.js'

document.addEventListener("DOMContentLoaded", function () {
    const nav = document.querySelector('#nav');
    const ul = document.createElement('ul');

    fetch(URL + API_NOTICE)
        .then(response => response.json())
        .then(data => {
            if (Array.isArray(data)) {
                const noticeHead = document.createElement('li');
                const noticeTitle = document.createElement('a');
                noticeTitle.href = URL + NOTICE_LIST;
                noticeTitle.className = 'font-nav-category-selected container-nav-category-selected';
                noticeTitle.textContent = '공지사항';
                const noticeContentsArea = document.createElement('div');
                const noticeContentsList = document.createElement('ul');

                const reversedData = data.reverse();
                const recentNotices = reversedData.slice(0, 5);
                const textLength = 11;

                recentNotices.forEach((item, index) => {
                    const li = document.createElement('li');
                    const title = document.createElement('a');
                    title.className = 'font-nav-title';
                    title.textContent = truncateText(item.title, textLength);
                    const description = document.createElement('a');
                    description.textContent = truncateText(item.content, textLength);

                    // 아래 구분선 처리
                    if (index !== recentNotices.length - 1) {
                        li.className = 'line-nav';
                    }

                    li.appendChild(title);
                    li.appendChild(description);
                    noticeContentsList.appendChild(li);
                });

                const postingFilter = document.createElement('li');
                postingFilter.className = 'container-nav-category'; // todo: 게시글 상태 필터링
                const postingPortal = document.createElement('a');
                postingPortal.href = URL + HOME; // todo: 게시글 상태 필터링
                postingPortal.textContent = '진행중인 공고';
                postingPortal.className = 'adoption-posting';
                postingPortal.className = 'font-nav-category';
                postingFilter.appendChild(postingPortal);

                const adoptedFilter = document.createElement('li');
                adoptedFilter.className = 'container-nav-category'; // todo: 게시글 상태 필터링
                const adoptedPortal = document.createElement('a');
                adoptedPortal.href = URL + HOME; // todo: 게시글 상태 필터링
                adoptedPortal.textContent = '완료된 공고';
                adoptedPortal.id = 'adoption-adopted';
                adoptedPortal.className = 'font-nav-category'
                adoptedFilter.appendChild(adoptedPortal);

                noticeContentsArea.appendChild(noticeContentsList);

                noticeHead.appendChild(noticeTitle);
                noticeHead.appendChild(noticeContentsArea);


                ul.appendChild(noticeHead);
                ul.appendChild(postingFilter);
                ul.appendChild(adoptedFilter);

                nav.appendChild(ul);
            } else {
                console.error('Received data is not an array:', data);
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

// <nav class="container-admin-nav">
//     <ul>
//         <li id='admin-nav-manage' class="padding-top-13 background-purple">
//             <p class="text-align-center font-nav-category-selected padding-bottom-15">관리자 페이지</p>
//             <div class="padding-top-19 padding-bottom-13 background-color-default">
//                 <ul>
//                     <li>
//                         <p class="font-nav-title">
//                             공지 관리
//                         </p>
//                         <p class="font-nav-description">
//                             공지 관리 페이지
//                         </p>
//                     </li>
//                     <li>
//                         <p class="font-nav-title">
//                             게시물 관리
//                         </p>
//                         <p class="font-nav-description">
//                             게시물 관리 페이지
//                         </p>
//                     </li>
//                     <li>
//                         <p class="font-nav-title">
//                             사용자 관리
//                         </p>
//                         <p class="font-nav-description margin-bottom-0">
//                             사용자 관리 페이지
//                         </p>
//                     </li>
//                 </ul>
//             </div>
//         </li>
//         <li class="font-nav-category frame-nav-normal background-nav text-align-center">
//             공지사항
//         </li>
//         <li class="frame-nav-normal background-nav text-align-center">
//             <a id="adoption-posting" class="font-nav-category">진행중인 공고</a>
//         </li>
//         <li class="frame-nav-normal background-nav text-align-center">
//             <a id="adoption-adopted" class="font-nav-category">완료된 공고</a>
//         </li>
//     </ul>
// </nav>