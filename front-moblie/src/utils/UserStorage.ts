import type { User } from '@/model/user'

class UserStorage {

  private static USER_STORAGE_KEY = "VIDEO_CENTER_USER_INFO_KEY";

  public static getUserInfo() :User | null {
    const json = localStorage.getItem(UserStorage.USER_STORAGE_KEY);
    if (json) {
      return JSON.parse(json);
    }
    return null;
  }

  public static setUserInfo(user: User) {
    localStorage.setItem(UserStorage.USER_STORAGE_KEY, JSON.stringify(user));
  }

  public static clean() {
    localStorage.removeItem(UserStorage.USER_STORAGE_KEY);
  }

}

export default UserStorage;