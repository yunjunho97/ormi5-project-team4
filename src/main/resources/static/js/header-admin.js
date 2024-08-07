import {URL, API_LOGOUT, ADMIN_PAGE, MY_PAGE, API_NOTICE, NOTICE_LIST, HOME} from './constant.js'
import {getMyInfo} from "./utils.js";

document.addEventListener("DOMContentLoaded", function () {
    const header = document.querySelector('#header-admin');
    const section = document.createElement('section');
    section.id = 'container-header-user';

    const logo = document.createElement('img')
    logo.src = '/images/logo.png'; // todo: 로고 이미지 제작
    logo.alt = '로고';
    logo.id = 'logo';

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
    logout.className = 'font-header';
    logout.textContent = '로그아웃';

    async function logoutEvent() {
        const response = await fetch(URL + API_LOGOUT, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            }
        });

        if (response.ok) {
            window.location.href = URL;
        } else {
            console.error("Something went wrong");
        }
    }

    logout.addEventListener('click', logoutEvent);

    const adminPage = document.createElement('a');
    adminPage.href = URL + ADMIN_PAGE;
    adminPage.className = 'font-header';
    adminPage.textContent = '관리자 페이지';

    userContainer.appendChild(username);
    userContainer.appendChild(welcomeText);

    section.appendChild(userContainer);
    section.appendChild(logout);
    section.appendChild(adminPage);

    header.appendChild(logo);
    header.appendChild(section);
});