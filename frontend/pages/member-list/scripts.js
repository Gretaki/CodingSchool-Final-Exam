import {deleteMemberById, getMembers} from "../../commons/requests.js";

const renderMembersTable = async (members) => {
const memberTableBody = document.getElementById("membersTable").querySelector("tbody");
members.forEach(member => {
    const memberRow = document.createElement("tr");

    const nameCell = document.createElement("td");
    nameCell.innerText = member.name;
    const lastNameCell = document.createElement("td");
    lastNameCell.innerText = member.lastName;
    const emailCell = document.createElement("td");
    emailCell.innerText = member.email;
    const genderCell = document.createElement("td");
    genderCell.innerText = member.gender;
    const ageCell = document.createElement("td");
    ageCell.innerText = member.age;
    const experienceCell = document.createElement("td");
    experienceCell.innerText = member.experience;

    const actionCell = document.createElement("td");

    const editButton = document.createElement("button");
    editButton.innerText = "EDIT";
    editButton.className = "btn btn-warning";
    editButton.style = "margin-right: 10px";
    editButton.addEventListener("click", async()=>{
        window.location.replace(`../edit-member/edit-member.html?id=${member.id}`);
    });

    const deleteButton = document.createElement("button");
    deleteButton.innerText = "DELETE";
    deleteButton.className = "btn btn-danger";
    deleteButton.style = "margin-right: 10px";
    deleteButton.addEventListener("click", async()=>{
        console.log("a");
        await deleteMemberById(member.id);
        window.location.reload();
    })

    actionCell.append(editButton, deleteButton);
    memberRow.append(nameCell, lastNameCell, emailCell, genderCell, ageCell, experienceCell, actionCell);
    memberTableBody.appendChild(memberRow);
});
}

const handleAddNewMemberButton = () => {
    document.getElementById("addMember").addEventListener("click", () => {
        window.location.replace("../add-member/add-member.html");
    })
}

(async () => {
    handleAddNewMemberButton();
    const members = await getMembers();
    console.log(members);
    renderMembersTable(members);
})();
