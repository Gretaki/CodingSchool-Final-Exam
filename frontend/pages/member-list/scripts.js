import { deleteMemberById, getMembers } from "../../commons/requests.js";

const renderMembersTable = async (members) => {
  const memberTableBody = document
    .getElementById("membersTable")
    .querySelector("tbody");

  memberTableBody.innerHTML = "";

  members.forEach((member) => {
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
    experienceCell.innerText = getExperience(member.monthsOfExperience);

    const actionCell = document.createElement("td");

    const editButton = document.createElement("button");
    editButton.innerText = "✏️";
    editButton.className = "btn btn-secondary btn-sm";
    editButton.style = "margin-right: 10px";
    editButton.addEventListener("click", async () => {
      window.location.replace(
        `../edit-member/edit-member.html?id=${member.id}`
      );
    });

    const deleteButton = document.createElement("button");
    deleteButton.innerText = "❌";
    deleteButton.className = "btn btn-secondary btn-sm";
    deleteButton.style = "margin-right: 10px";
    deleteButton.addEventListener("click", async () => {
      console.log("a");
      await deleteMemberById(member.id);
      window.location.reload();
    });

    actionCell.append(editButton, deleteButton);
    memberRow.append(
      nameCell,
      lastNameCell,
      emailCell,
      genderCell,
      ageCell,
      experienceCell,
      actionCell
    );
    memberTableBody.appendChild(memberRow);
  });
};

const getExperience = (monthsOfExperience) => {
  console.log(monthsOfExperience);
  function getYearsPlural(years) {
    return years === 1 ? "year" : "years";
  }

  function getMonthsPlural(months) {
    return months === 1 ? "month" : "months";
  }

  const years = Math.floor(monthsOfExperience / 12);
  const months = monthsOfExperience % 12;

  const result = [];
  years && result.push(years + " " + getYearsPlural(years));
  months && result.push(months + " " + getMonthsPlural(months));
  return result.join(" and ");
};

const handleAddNewMemberButton = () => {
  document.getElementById("addMember").addEventListener("click", () => {
    window.location.replace("../add-member/add-member.html");
  });
};

const loadPaginationNumbers = (totalPages, currentPage) => {
  const pagination = document.getElementById("pagination");
  pagination.innerHTML = "";

  const list = document.createElement("ul");
  list.classList.add("pagination", "justify-content-center");

  const pageNumbers = [];

  pageNumbers.push(currentPage);
  if (currentPage > 0) {
    pageNumbers.push(currentPage - 1);
  } else if (currentPage + 2 < totalPages) {
    pageNumbers.push(currentPage + 2);
  }
  if (currentPage < totalPages - 1) {
    pageNumbers.push(currentPage + 1);
  } else if (currentPage > 1) {
    pageNumbers.push(currentPage - 2);
  }

  console.log(pageNumbers.sort((a, b) => a - b));

  const elements = pageNumbers.map((pagenumber) => {
    const activityValue = currentPage === pagenumber ? "active" : "";
    const element = loadElement(activityValue, pagenumber + 1);
    element.addEventListener("click", async () => {
      const members = await getMembers(pagenumber, 10);
      loadPaginationNumbers(members.totalPages, members.currentPage);
      renderMembersTable(members.members);
    });
    return element;
  });

  elements.forEach((element) => list.append(element));

  pagination.append(list);
};

const loadElement = (activityValue, innerText) => {
  const element = document.createElement("li");
  if (activityValue != "") {
    element.classList.add("page-item", activityValue);
  } else {
    element.classList.add("page-item");
  }

  const elementLink = document.createElement("a");
  elementLink.className = "page-link";
  elementLink.innerText = innerText;

  element.append(elementLink);

  return element;
};

(async () => {
  handleAddNewMemberButton();

  const members = await getMembers(0, 10);
  loadPaginationNumbers(members.totalPages, members.currentPage);
  renderMembersTable(members.members);
})();
