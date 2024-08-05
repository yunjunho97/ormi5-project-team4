import {URL, API_LOGOUT, ADMIN_PAGE, MY_PAGE, API_NOTICE, NOTICE_LIST, HOME} from './constant.js'
import {getMyInfo} from "./utils.js";

document.addEventListener("DOMContentLoaded", function () {
    const header = document.querySelector('#header');
    const section = document.createElement('section');
        section.id = 'container-header-user';

    const logo = document.createElement('img')
        logo.src = ''; // todo: 로고 이미지 제작
        logo.alt = '로고';

    const userContainer = document.createElement('div');
    const username = document.createElement('p');
        username.className = 'font-header-username';
        getMyInfo().then(data =>{
            username.textContent = data.userName;
        }).catch(error =>{
            console.error('error: ', error)
        });

    const welcomeText = document.createElement('p');
        welcomeText.className = 'font-header';
        welcomeText.textContent = ' 님 환영합니다';

    const logout = document.createElement('a');
        logout.href = URL + API_LOGOUT;
        logout.className = 'font-header';
        logout.textContent = '로그아웃';
    const myPage = document.createElement('a');
        myPage.href = URL + MY_PAGE;
        myPage.className = 'font-header';
        myPage.textContent = '마이페이지';

    userContainer.appendChild(username);
    userContainer.appendChild(welcomeText);

    section.appendChild(userContainer);
    section.appendChild(logout);
    section.appendChild(myPage);

    header.appendChild(logo);
    header.appendChild(section);
});