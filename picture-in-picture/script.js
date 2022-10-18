const videoElement = document.getElementById("video");
const button = document.getElementById("button");
// prompt to select media stream ,pass to video element ,then play
async function selectMediaStream() {
  try {
    const mediaStream = await navigator.mediaDevices.getDisplayMedia();
    videoElement.srcObject = mediaStream;
    videoElement.onloadeddatametadata = () => {
      videoElement.play();
    };
  } catch (error) {
    console.log("whoops ,error here:", error);
  }
}
button.addEventListener("click", async () => {
  // disable button
  button.disable = true;
  // start picture in picture
  await videoElement.requestPictureInPicture();
  button.disable = false;
});
selectMediaStream();
