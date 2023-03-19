const handleAddNewMemberButton = () => {
  document.getElementById("addMember").addEventListener("click", () => {
    window.location.replace("../add-member/add-member.html");
  });
};

(async () => {
  handleAddNewMemberButton();
})();
