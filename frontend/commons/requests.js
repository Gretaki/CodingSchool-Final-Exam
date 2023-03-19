const API_BASE_URL = "http://localhost:8080";

export const getMembers = async (page = 0, size = 5) => {
  const response = await fetch(`${API_BASE_URL}/chess-club?page=${page}&size=${size}`);
  if (response.status === 200){
    const members = await response.json();
    return members;
} else {
    alert("Error getting members, please try again");
}
};

export const getMemberByID = async (memberId) => {
    const response = await fetch(`${API_BASE_URL}/chess-club/${memberId}`);
    if (response.status === 200){
        const member = await response.json();
        return member;
    } else {
        alert("Error getting member, please try again");
    }
  };

  export const saveMember = async (member) => {
    const response = await fetch(`${API_BASE_URL}/chess-club`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(member),
    });
  
    if (response.status === 200){
        alert("Member saved successfully!");
    } else {
        alert("Error saving member, please try again");
    }
  };
  
  export const editMember = async (member, memberId) => {
    const response = await fetch(`${API_BASE_URL}/chess-club/${memberId}`, {
      method: "PATCH",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(member),
    });
  
    if (response.status === 200){
        alert(`Member with id: ${memberId} updated successfully!`);
    } else {
        alert("Error updating member, please try again");
    }

    
  };
  
  export const deleteMemberById = async (memberId) => {
    const response = await fetch(`${API_BASE_URL}/chess-club/${memberId}`, {
        method: "DELETE",
    });
  
    if (response.status === 200){
        alert(`Member with id: ${memberId} deleted successfully`);
    } else {
        alert("Error deleting member, please try again");
    }
  };
  