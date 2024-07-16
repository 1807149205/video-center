import type{ AxiosInstance, AxiosResponse } from 'axios';
import axios from "axios";
import router from "@/router";
import { showNotify } from 'vant'

interface ApiResponse<T> {
  code: number;
  msg: string;
  data: T;
}

class HTTPClient {
  private axiosInstance: AxiosInstance;

  private baseURL: string;

  constructor(baseURL: string) {
    this.baseURL = baseURL;
    this.axiosInstance = axios.create({
      baseURL,
      timeout: 80000, // 设置超时时间
      headers: {
        "Content-Type": "application/json",
      }
    });
  }

  private handleResponse<T>(response: AxiosResponse<ApiResponse<T>>): T {
    const responseData = response.data;


    if (responseData.code === 410) {
      showNotify({ type: 'danger', message: `提示:${responseData.msg}` });
      throw new Error(`Request failed with code ${responseData.code}: ${responseData.msg}`);
    }

    return responseData.data;
  }

  public async post<T>(url: string, data?: any, headers?: any): Promise<T> {
    const response = await this.axiosInstance.post<ApiResponse<T>>(url, data, { headers });
    return this.handleResponse(response);
  }

  public async get<T>(url: string, params?: object): Promise<T> {
    const response = await this.axiosInstance.get<ApiResponse<T>>(url, params);
    return this.handleResponse(response);
  }

  public setUrl(url: string): void {
    this.baseURL = url;
  }

  public getUrl(): string {
    return this.baseURL;
  }
}

export default new HTTPClient("http://127.0.0.1:8212");