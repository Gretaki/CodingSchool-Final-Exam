import { getMemberByID, editMember } from "../../commons/requests.js";
import { validateEmail, validatePersonalCode, validateStartDate } from "../../commons/utils.js";

const editMemberForm = document
  .getElementById("editMemberForm")
  .querySelector("form");

let oldMemberData;

const loadMemberData = async () => {
  const memberId = new URLSearchParams(window.location.search).get("id");

  oldMemberData = await getMemberByID(memberId);
  editMemberForm.name.value = oldMemberData.name;
  editMemberForm.lastName.value = oldMemberData.lastName;
  editMemberForm.email.value = oldMemberData.email;
  editMemberForm.personalCode.value = oldMemberData.personalCode;
  editMemberForm.startDate.value = oldMemberData.startDate;
};

const handleFormSubmit = async () => {
  editMemberForm.addEventListener("submit", async (e) => {
    e.preventDefault();

    if (!validateEmail(editMemberForm.email.value)) {
      alert("Email is not valid. Try one more time");
      return;
    }

    if (!validatePersonalCode(editMemberForm.personalCode.value)) {
      alert("Personal code is not valid. Try one more time");
      return;
    }

    if (!validateStartDate(editMemberForm.startDate.value)) {
      alert("Start date is not valid. Try one more time");
      return;
    }

    const member = {
      name:
        oldMemberData.name !== editMemberForm.name.value
          ? editMemberForm.name.value
          : undefined,
      lastName:
        oldMemberData.lastName !== editMemberForm.lastName.value
          ? editMemberForm.lastName.value
          : undefined,
      email:
        oldMemberData.email !== editMemberForm.email.value
          ? editMemberForm.email.value
          : undefined,
      personalCode:
        oldMemberData.personalCode !== editMemberForm.personalCode.value
          ? editMemberForm.personalCode.value
          : undefined,
      startDate:
        oldMemberData.startDate !== editMemberForm.startDate.value
          ? editMemberForm.startDate.value
          : undefined,
    };

    await editMember(member, oldMemberData.id);
    window.location.replace("../member-list/member-list.html");
  });
};

const handleCancelButton = () => {
  document.getElementById("cancelButton").addEventListener("click", () => {
    window.location.replace("../member-list/member-list.html");
  });
};

(async () => {
  await loadMemberData();
  handleCancelButton();
  await handleFormSubmit();
})();
