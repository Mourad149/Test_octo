import React from "react";
import logo from "./logo.svg";
import "./App.css";

import {
  Typography,
  FormControl,
  InputLabel,
  Select,
  MenuItem,
  TextField,
  Button,
} from "@material-ui/core";
import axios from "axios";
import ReactPlayer from "react-player";

function App(props) {
  const [level, setLevel] = React.useState("");
  const [tags, setTags] = React.useState("");
  const [videos, setVideos] = React.useState([]);

  const handleLevelChange = (e) => {
    console.log(e);
    setLevel(e.target.value);
  };
  const handleTagsChange = (e) => {
    setTags(e.target.value);
  };
  const handleSubmit = () => {
    if (level !== "" || tags !== "") {
      axios(
        `https://localhost:8888/api/v1/videos?level=${level}&tags=${tags
          .slice(1)
          .split("#")}`,
        {
          methode: "GET",
          headers: {
            "Access-Control-Allow-Origin": "*",
            "Content-Type": "application/json",
          },
          mode: "no-cors",
        }
      )
        .then((res) => {
          setVideos(res.data);
          console.log(res);
        })
        .catch((err) => console.log(err));
    }
  };

  return (
    <div style={{ height: "100%", width: "100%" }}>
      <div
        style={{
          display: "flex",
          flexDirection: "column",
          alignItems: "center",
          justifyContent: "center-around",
          width: "100%",
        }}
      >
        <Typography style={{ fontSize: "3rem" }}>Select Videos By</Typography>
        <FormControl
          style={{
            width: "25%",
            margin: "20px",
          }}
        >
          <InputLabel style={{ fontSize: "1.5rem" }}>level</InputLabel>
          <Select
            labelId="demo-simple-select-label"
            id="demo-simple-select"
            value={level}
            onChange={handleLevelChange}
          >
            <MenuItem value={"ELEMENTARY"}> ELEMENTARY</MenuItem>
            <MenuItem value={"EASY"}>EASY</MenuItem>
            <MenuItem value={"MEDIUM"}>MEDIUM</MenuItem>
            <MenuItem value={"HARD"}>HARD</MenuItem>
            <MenuItem value={"NONE"}>NOT SPECIFIED</MenuItem>
          </Select>
        </FormControl>

        <TextField
          type="text"
          value={tags}
          onChange={handleTagsChange}
          placeholder="Insert tags like #POURCENTAGE#CALCULE"
          style={{
            width: "25%",
            margin: "20px",
          }}
        />
        <Button
          onClick={handleSubmit}
          style={{
            height: "4rem",
            width: "20rem",
            fontSize: "2rem",
            backgroundColor: "red",
            color: "white",
          }}
        >
          Submit
        </Button>
      </div>
      <div
        style={{
          display: "flex",
          flexDirection: "column",
          alignItems: "center",
          justifyContent: "center",
        }}
      >
        {videos.map((video, index) => (
          <div style={{ display: "flex", flexDirection: "column" }}>
            <Typography>{video.title}</Typography>
            <ReactPlayer url={video.url} />
            <Typography>{video.description}</Typography>
            <Typography>tags : {video.tags.map((tag) => "#" + tag)}</Typography>
            <Typography>LEVEL : {video.level}</Typography>
          </div>
        ))}
      </div>
    </div>
  );
}

export default App;
