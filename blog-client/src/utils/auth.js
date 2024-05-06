import Cookies from 'js-cookie';
export const getCookiesToken = () => {
  return Cookies.get('token');
};

export const setCookiesToken = token => {
  return Cookies.set('token', token);
};

export const removeCookiesToken = () => {
  return Cookies.remove('token');
};
