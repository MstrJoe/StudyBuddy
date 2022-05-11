function getErrorFromResponse(err) {
  const errorData = err.response?.data;

  if (errorData) {
    return `${errorData[0].field}: ${errorData[0].defaultMessage}`;
  }
}

export function handleError(err) {
  const errorMessage = getErrorFromResponse(err);

  if (errorMessage) {
    alert(errorMessage);
  } else {
    alert('Something went wrong');
  }
}
