*, *:before, *:after {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}

.pendulum-holder {
  width: 400px;
  height: 300px;
  position: relative;
  margin: auto;
  border-top: 3px solid #2b88af;
}

.pendulum {
  outline: 2px solid transparent;
  position: absolute;
  left: 50%;
  transform-origin: 50% 0;
  transform: translate(-50%) rotate(20deg);
  width: 2px;
  height: 150px;
  background: #4d7d91;
  animation: animateIt 1.5s ease-in-out infinite;
}

.pendulum:nth-of-type(2) {
  height: 155px;
  animation-duration: 1.55s;
}
.pendulum:nth-of-type(3) {
  height: 160px;
  animation-duration: 1.6s;
}
.pendulum:nth-of-type(4) {
  height: 165px;
  animation-duration: 1.65s;
}
.pendulum:nth-of-type(5) {
  height: 170px;
  animation-duration: 1.7s;
}
.pendulum:nth-of-type(6) {
  height: 175px;
  animation-duration: 1.75s;
}
.pendulum:nth-of-type(7) {
  height: 180px;
  animation-duration: 1.8s;
}
.pendulum:nth-of-type(8) {
  height: 185px;
  animation-duration: 1.85s;
}
.pendulum:nth-of-type(9) {
  height: 190px;
  animation-duration: 1.9s;
}
.pendulum:nth-of-type(10) {
  animation-duration: 1.95s;
  height: 195px;
}
.pendulum:nth-of-type(11) {
  animation-duration: 2s;
  height: 200px;
}
.pendulum:nth-of-type(12) {
  height: 205px;
  animation-duration: 2.05s;
}
.pendulum:nth-of-type(13) {
  height: 210px;
  animation-duration: 2.1s;
}
.pendulum:nth-of-type(14) {
  height: 215px;
  animation-duration: 2.15s;
}
.pendulum:nth-of-type(15) {
  height: 220px;
  animation-duration: 2.2s;
}

@keyframes animateIt {
  0%, 100% {
    transform: translate(-50%) rotate(25deg);
  }
  50% {
    transform: translate(-50%) rotate(-25deg);
  }
}

.pendulum:before, .pendulum:after {
  content: "";
  position: absolute;
  left: 50%;
  border-radius: 50%;
  transform: translate(-50%);
}

.pendulum:before {
  top: 100%;
  width: 15px;
  height: 15px;
  background: -moz-radial-gradient(circle at 70% 35%, white, #1eafed 30%, #2b88af 50%);
  background: -webkitradial-gradient(circle at 70% 35%, white, #1eafed 30%, #2b88af 50%);
  background: -o-radial-gradient(circle at 70% 35%, white, #1eafed 30%, #2b88af 50%);
  background: -ms-radial-gradient(circle at 70% 35%, white, #1eafed 30%, #2b88af 50%);
  background: radial-gradient(circle at 70% 35%, white, #1eafed 30%, #2b88af 50%);
}

.pendulum:after {
  background: rgba(0, 0, 0, 0.2);
  top: 120%;
  filter: blur(4px);
  width: 20px;
  height: 3px;
}