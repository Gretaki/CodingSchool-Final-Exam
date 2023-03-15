import { saveMember } from "../../commons/requests.js";
import {
  validateEmail,
  validatePersonalCode,
  validateStartDate,
} from "../../commons/utils.js";

const handleFormSubmit = async () => {
  const form = document.getElementById("addMemberForm").querySelector("form");
  form.addEventListener("submit", async (e) => {
    e.preventDefault();

    if (!validateEmail(form.email.value)) {
      alert("Email is not valid. Try one more time");
      return;
    }

    if (!validatePersonalCode(form.personalCode.value)) {
      alert("Personal code is not valid. Try one more time");
      return;
    }

    if (!validateStartDate(form.startDate.value)) {
      alert("Start date is not valid. Try one more time");
      return;
    }

    const member = {
      name: form.name.value,
      lastName: form.lastName.value,
      email: form.email.value,
      personalCode: form.personalCode.value,
      startDate: form.startDate.value,
    };
    await saveMember(member);
    window.location.reload();
  });
};

const handleCancelButton = () => {
  document.getElementById("cancelButton").addEventListener("click", () => {
    window.location.replace("../member-list/member-list.html");
  });
};

(async () => {
  handleCancelButton();
  await handleFormSubmit();
})();
