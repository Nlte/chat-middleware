package main

import (
  "os"
  "log"
  "net/http"
  "encoding/json"
)

type auth_request struct {
  Login string `json:"login"`
  Password string `json:password`
}

type auth_response struct {
  Success bool `json:"success"`
}

type users struct {
  Users []struct {
    Login string
    Password string
  }
}

func AuthHandler(resp http.ResponseWriter, req *http.Request) {
  log.Println("Authentication request from: ", req.RemoteAddr)
  if (req.Method != "POST") {
    log.Println("Bad authentication request, got a HTTP ", req.Method)
    resp.WriteHeader(http.StatusMethodNotAllowed)
    return
  }
  decoder := json.NewDecoder(req.Body)
  areq := new(auth_request)
  err := decoder.Decode(areq)
  if err != nil {
    log.Println("Bad authentication request: ", err)
  } else {
    response := auth_response{Success: false}
    password, present := Users[areq.Login]
    if present && password == areq.Password {
      response.Success = true
    }
    resp.Header().Set("Content-Type", "application/json")
    encoder := json.NewEncoder(resp)
    err = encoder.Encode(response)
    if err != nil {
      log.Println("Could not encode a JSON response: ", err)
    }
  }
}

var Users map[string]string

func loadConfiguration() {
  file, err := os.Open("users.json")
  if err != nil {
    log.Fatal("Could not open users.json: ", err)
  }
  decoder := json.NewDecoder(file)
  users := new(users)
  err = decoder.Decode(users)
  if err != nil {
  log.Fatal("Could not parse users.json: ", err)
  }
  Users = map[string]string{}
  for _, user := range users.Users {
    Users[user.Login] = user.Password
  }
  log.Println("Users loaded")
}

func main() {
  loadConfiguration()
  http.HandleFunc("/auth", AuthHandler)
  log.Println("Serving requests on port 8666")
  err := http.ListenAndServe(":8666", nil)
  if err != nil {
    log.Fatal("Could not start the server: ", err)
  }
}
