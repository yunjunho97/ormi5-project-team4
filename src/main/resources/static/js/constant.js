export const URL = "http://localhost:8080";

// API 영역
export const API_LOGIN = "/auth/login";
export const API_LOGOUT = '/auth/logout';
export const API_NOTICE = '/notice';
export const API_ADMIN_GET_POSTS = "/admin/post";

// 페이지 영역
export const HOME = '/home';
export const MANAGE_POST = '/manage/posts';
export const READ_POST = '/read-post/'; // pathVariable 이 항상 있음
export const MY_PAGE = '/this/info';
export const ADMIN_PAGE = '/manage/posts';
export const NOTICE_LIST = '/notice-list';

// Parameter 영역
export const PAGE_ID = getPageId();
export const PAGE_ID_PREVIOUS = PAGE_ID - 1;
export const PAGE_ID_NEXT = PAGE_ID + 1;
export const APPROVE_STATUS = getApproveStatus();
export const ADOPTION_STATUS = getAdoptionStatus();

// export Function 영역
export function getResponseForAdoptionStatus(adoptionStatus) {
    switch(adoptionStatus) {
        case 'POSTING':
            return '[ 진행중인 공고 ]';
        case 'PROCEEDING':
            return '[ 진행중인 공고 ]';
        case 'ADOPTED':
            return '[ 완료된 공고 ]';
    }
}

export function getPageStartNumber(page, pageSize){
    return page <= 5 ? 1 : page >= pageSize - 4 ? pageSize - 8 : page - 4;
}

export function getPageEndNumber(page, pageSize){
    return page <= 5 ? 9 : page >= pageSize - 4 ? pageSize : page + 4;
}

// function 영역
function getUrlParameter(name) {
    const urlParams = new URLSearchParams(window.location.search);
    return urlParams.get(name) || '';
}

function getPageId() {
    const pageParam = getUrlParameter('page');
    return (isNaN(pageParam) || pageParam == null) ? 0 : parseInt(pageParam, 10);
}

function getApproveStatus() {
    return getUrlParameter('approvestatus') || '';
}

function getAdoptionStatus(){
    return getUrlParameter('adoptionstatus') || '';
}