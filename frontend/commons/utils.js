export const validateEmail = (email) => {
  const regex =
    /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/i;
  return regex.test(email);
};

export const validatePersonalCode = (personalCode) => {
  if (
    personalCode.length == 11 &&
    personalCode > 0 &&
    personalCode.substring(0, 1) < 7 &&
    personalCode.substring(3, 5) < 12 &&
    personalCode.substring(5, 7) < 31
  ) {
    return true;
  }
  return false;
};

export const validateStartDate = (startDate) => {
  return new Date(startDate) <= new Date(new Date().toISOString().slice(0, 10));
};
