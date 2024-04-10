import Cookies from 'js-cookie'

const TokenKey = 'vue_admin_template_token'

export function getToken() {
  // return 'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzeWtqX2FkbWluIiwiYWNjZXNzVHlwZSI6MTEsInVzZXJUeXBlIjoyLCJ1c2VySWQiOjE3NjUyNjc2MjU1OTk3Mzc4NTksImV4cCI6MTcxMDgzMjEyOSwianRpIjoiMTk1ZjM2ODAtNTU1ZC00NTRhLTg1OTUtODMyYWMyNTIyZmJlIn0.2uFIx6kLi8JS0nDTXx7tSRAodEnDTip1Jwt3qY5H81c'
  return Cookies.get(TokenKey)
}

export function setToken(token) {
  return Cookies.set(TokenKey, token)
}

export function removeToken() {
  return Cookies.remove(TokenKey)
}
