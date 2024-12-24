document.getElementById("signupForm").addEventListener("submit", function(event) {
    const password = document.getElementById("password").value;
    const confirmPassword = document.getElementById("confirmPassword").value;
    const message = document.getElementById("message");
    const securityQuestion = document.getElementById("securityQuestion").value;
    const securityAnswer = document.getElementById("securityAnswer").value;

    if (password !== confirmPassword) {
        event.preventDefault();
        message.textContent = "비밀번호가 일치하지 않습니다. 다시 입력해주세요.";
        message.className = "error";
    } else if (securityQuestion === "" || securityAnswer.trim() === "") {
        event.preventDefault();
        message.textContent = "본인 확인용 질문과 답을 모두 입력해주세요.";
        message.className = "error";
    } else {
        message.textContent = ""; // 오류 메시지 제거
        message.className = "";
        // 폼 제출은 정상적으로 진행됩니다.
    }
});