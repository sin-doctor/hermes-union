document.getElementById("signupForm").addEventListener("submit", function(event) {
    event.preventDefault(); // 기본 제출 동작 막기

    const password = document.getElementById("user_pw").value;
    const confirmPassword = document.getElementById("confirmPassword").value;
    const message = document.getElementById("message");

    if (password === confirmPassword) {
        message.textContent = "비밀번호가 일치합니다.";
        message.className = "success";
        // 비밀번호가 일치하면 폼 제출
        this.submit();
    } else {
        message.textContent = "비밀번호가 일치하지 않습니다. 다시 입력해주세요.";
        message.className = "error";
        // 비밀번호 불일치 시 제출되지 않음
    }
});