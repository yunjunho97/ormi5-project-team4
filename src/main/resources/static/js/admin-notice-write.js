import {
    ADMIN_NOTICE, API_ADMIN_NOTICE,
    URL
} from './constant.js';
import {
    getMyInfo
} from "./utils.js";

document.addEventListener('DOMContentLoaded', function () {


    // 제목 확정 및 취소 버튼
    const titleApplyButton = document.querySelector('#notice-title-apply');
    titleApplyButton.addEventListener('click', function () {
        document.querySelector('#notice-title').disabled = true;
    });

    const titleDenyButton = document.querySelector('#notice-title-deny');
    titleDenyButton.addEventListener('click', function () {
        document.querySelector('#notice-title').disabled = false;
    });

    // 내용 박스 크기 자동조절
    const noticeContent = document.querySelector('#notice-content');
    noticeContent.addEventListener('input', function () {
        this.style.height = 'auto';
        this.style.height = Math.max(this.scrollHeight, 100) + 'px';
    });

    // 게시 확정 및 취소 버튼
    const noticeWriteButton = document.querySelector('#notice-write-apply');
    noticeWriteButton.addEventListener('click', async function () {

        // 제목 확인을 눌러야 게시가 가능함
        if (document.querySelector('#notice-title').disabled) {
            const title = document.querySelector('#notice-title').value;
            const content = document.querySelector('#notice-content').value;
            const userInfo = await getMyInfo();
            const userId = userInfo.id;
            console.log(userId);

            const data = {
                title: title,
                content: content,
                userId: userId
            }
            console.log(data);
            console.log(JSON.stringify(data));

            fetch(URL + API_ADMIN_NOTICE, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(data)
            })
                .then(response => response.json())
                .then(data => {
                    window.location.href = URL + ADMIN_NOTICE;
                })
                .catch((error) => {
                    console.error("Error: ", error);
                });
        } else {
            alert("게시글 제목을 확정하세요");
        }
    });

    const cancelButton = document.querySelector('#notice-write-deny');
    cancelButton.addEventListener('click', function () {
        window.location.href = URL + ADMIN_NOTICE;
    });
});