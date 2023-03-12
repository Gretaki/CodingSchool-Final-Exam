import { getMemberByID, editMember } from "../../commons/requests.js";

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
};

const handleFormSubmit = async () => {
  editMemberForm.addEventListener("submit", async (e) => {
    e.preventDefault();

    const member = {
      name:
      oldMemberData.name !== editMemberForm.name.value
          ? editMemberForm.name.value
          : undefined,
      lastname:
      oldMemberData.lastname !== editMemberForm.lastName.value
          ? editMemberForm.lastName.value
          : undefined,
          email:
      oldMemberData.email !== editMemberForm.email.value
          ? editMemberForm.email.value
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
