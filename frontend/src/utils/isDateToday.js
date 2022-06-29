import dayjs from 'dayjs';

export function isDateToday(date) {
  if (!date) {
    return false;
  }

  return dayjs(new Date()).format('YYYY-MM-DD') === dayjs(date).format('YYYY-MM-DD');
}
