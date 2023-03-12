const API_BASE_URL = "http://localhost:8080";

export const getMembers = async () => {
  const response = await fetch(`${API_BASE_URL}/chess-club`);
  const members = await response.json();
  return members;
};

export const getMemberByID = async (memberId) => {
    const response = await fetch(`${API_BASE_URL}/chess-club/${memberId}`);
    const member = await response.json();
    return member;
  };

  export const saveMember = async (member) => {
    await fetch(`${API_BASE_URL}/chess-club`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(member),
    });
  
    alert("Member saved successfully!");
  };
  
  export const editMember = async (member, memberId) => {
    await fetch(`${API_BASE_URL}/chess-club/${memberId}`, {
      method: "PATCH",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(member),
    });
  
    alert(`[Member ${memberId}] updated successfully!`);
  };
  
  export const deleteMemberById = async (memberId) => {
    console.log(`${API_BASE_URL}/chess-club/${memberId}`);
    await fetch(`${API_BASE_URL}/chess-club/${memberId}`, {
        method: "DELETE",
    });
  
    alert(`[Member ${memberId}] deleted successfully`);
  };
  