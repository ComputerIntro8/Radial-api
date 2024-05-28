let resp;
function getURLParameter(sParam) {
  var sPageURL = window.location.search.substring(1);
  var sURLVariables = sPageURL.split('&');
  for (var i = 0; i < sURLVariables.length; i++) {
    var sParameterName = sURLVariables[i].split('=');
    if (sParameterName[0] == sParam) {
      return sParameterName[1];
    }
  }
}
function getSurveyStatus(userId) {
  let xhttp = new XMLHttpRequest();
  xhttp.onreadystatechange = function () {
    if (xhttp.readyState == 4 && xhttp.status == 409) {
      location.href = '/complete.html';
    } else if (xhttp.readyState == 4 && xhttp.status == 200) {
      resp = JSON.parse(xhttp.responseText);
      console.log(resp);
    }
  };
  xhttp.open("GET", `/api/survey/${userId}`, false);
  xhttp.send();
}
const userId = getURLParameter('userId');
getSurveyStatus(userId);
const d1 = resp[0].timeDataLevels.map((d) => { 
  const date = new Date(d.time);
  date.setHours(date.getHours() - 15);
  return { created_at: date, value: d.dataLevel }; 
});
const d2 = resp[2].timeDataLevels.map((d) => { 
  const date = new Date(d.time);
  date.setHours(date.getHours() - 15);
  return { created_at: date, value: d.dataLevel }; 
});
const sample_data = [];
let sample_values = [
  0,5,10,25,55,100,40,30,30,30,33,35,50,55,10,16,64,56
];
for (let i = 0 ; i < sample_values.length; i++) {
  sample_data.push({
    value: sample_values[i],
    created_at: new Date(1000 * 60 * 30 * i + 1000 * 60 * 60 * 15)
  });
}
const questions_meta = [
  {
    plot_type: 'radial',
    interval: 'day',
    min_y: 0,
    max_y: 100,
    question: '다음과 같은 그래프를 보고 지문에 따라 문제를 해결하시오. 3번을 선택해 다음 문제로 넘어가면 시작됩니다.',
    data: sample_data,
    answers: [{ answerId: 1, answerText: "1" }, { answerId: 2, answerText: "2" }, { answerId: 3, answerText: "3" }, { answerId: 4, answerText: "4" }, { answerId: 5, answerText: "모르겠음" }, ]
  },
  {
    plot_type: 'line',
    min_x: d1[0].created_at,
    max_x: d1[d1.length - 1].created_at,
    min_y: 0,
    max_y: 100,
    question: resp[0].questionText,
    data: d1,
    answers: resp[0].answers
  },
  {
    plot_type: 'radial',
    interval: 'day',
    min_y: 0,
    max_y: 100,
    question: resp[1].questionText,
    data: resp[1].timeDataLevels.map((d) => { 
      const date = new Date(d.time);
      date.setHours(date.getHours() - 15);
      return { created_at: date, value: d.dataLevel }; 
    }),
    answers: resp[1].answers
  },
  {
    plot_type: 'line',
    min_x: d2[0].created_at,
    max_x: d2[d2.length - 1].created_at,
    min_y: 0,
    max_y: 100,
    question: resp[2].questionText,
    data: d2,
    answers: resp[2].answers
  },
  {
    plot_type: 'radial',
    interval: 'day',
    min_y: 0,
    max_y: 120,
    question: resp[3].questionText,
    data: resp[3].timeDataLevels.map((d) => { return { created_at: new Date(d.time), value: d.dataLevel }; }),
    answers: resp[3].answers
  },
  {
    plot_type: 'radial',
    min_y: 0,
    max_y: 200,
    interval: 'week',
    question: resp[4].questionText,
    data: resp[4].timeDataLevels.map((d) => { return { created_at: new Date(d.time), value: d.dataLevel }; }),
    answers: resp[4].answers
  },
  {
    plot_type: 'radial',
    interval: 'day',
    min_y: 40,
    max_y: 80,
    question: resp[5].questionText,
    data: resp[5].timeDataLevels.map((d) => { return { created_at: new Date(d.time), value: d.dataLevel }; }),
    answers: resp[5].answers
  },
  {
    plot_type: 'line',
    min_x: new Date(resp[6].timeDataLevels[0].time),
    max_x: new Date(resp[6].timeDataLevels[resp[6].timeDataLevels.length - 1].time),
    min_y: 0,
    max_y: 100,
    question: resp[6].questionText,
    data: resp[6].timeDataLevels.map((d) => { return { created_at: new Date(d.time), value: d.dataLevel }; }),
    answers: resp[6].answers
  },
  {
    plot_type: 'line',
    min_x: new Date(resp[7].timeDataLevels[0].time),
    max_x: new Date(resp[7].timeDataLevels[resp[7].timeDataLevels.length - 1].time),
    min_y: 0,
    max_y: 40,
    question: resp[7].questionText,
    data: resp[7].timeDataLevels.map((d) => { return { created_at: new Date(d.time), value: d.dataLevel }; }),
    answers: resp[7].answers
  },
  {
    plot_type: 'line',
    min_x: new Date(resp[8].timeDataLevels[0].time),
    max_x: new Date(resp[8].timeDataLevels[resp[8].timeDataLevels.length - 1].time),
    min_y: 0,
    max_y: 150,
    question: resp[8].questionText,
    data: resp[8].timeDataLevels.map((d) => { return { created_at: new Date(d.time), value: d.dataLevel }; }),
    answers: resp[8].answers
  },
  {
    plot_type: 'radial',
    min_y: 38000,
    max_y: 46000,
    interval: 'month',
    question: resp[9].questionText,
    data: resp[9].timeDataLevels.map((d) => { return { created_at: new Date(d.time), value: d.dataLevel }; }),
    answers: resp[9].answers
  },
];
const answers = [];
const question_size = 10;
let num = 0;
function init_canvas(question) {
  let canvas = document.getElementById("tutorial");
  let context = canvas.getContext("2d");
  let rect = canvas.getBoundingClientRect();

  canvas.width = window.innerWidth;
  canvas.height = window.innerWidth;
  const width = canvas.width;
  const height = canvas.height;
  const center = width / 2;
  const radius = center * 0.9;
  const radiusSmall = center * 0.3;
  const min = question.min_y;
  const max = question.max_y;


  // line chart에서 사용
  let x_start = question.min_x ? question.min_x.getTime() : null;
  let x_end = question.min_x ? question.max_x.getTime() : null;

  canvas.width = rect.width * devicePixelRatio;
  canvas.height = rect.height * devicePixelRatio;
  context.scale(devicePixelRatio, devicePixelRatio);

  canvas.style.width = rect.width + 'px';
  canvas.style.height = rect.height + 'px';
  const ctx = canvas.getContext("2d");
  ctx.font = center < 200 ? "10px Arial" : "14px Arial";
  ctx.textAlign = 'center';
  ctx.textBaseline = 'middle';
  function draw_radial_data(color, data) {
    ctx.lineWidth = 1.5;
    ctx.strokeStyle = color;
    if (question.interval === 'day') {
      for (let i = 0; i < data.length - 1; i++) {
        const date1 = data[i].created_at;
        const x1 = date1.getSeconds() + date1.getMinutes() * 60 + date1.getHours() * 3600;
        const y1 = data[i].value;
        const date2 = data[i + 1].created_at;
        const x2 = date2.getSeconds() + date2.getMinutes() * 60 + date2.getHours() * 3600;
        const y2 = data[i + 1].value;
        const v1 = (y1 - min) / (max - min) * (radius - radiusSmall) + radiusSmall;
        const v2 = (y2 - min) / (max - min) * (radius - radiusSmall) + radiusSmall;
        if (date2.getTime() - date1.getTime() > 1000 * 60 * 60 * 24)
          continue;
        ctx.beginPath();
        ctx.lineTo(
          center + Math.cos(Math.PI / 43200 * (x1 - 3600 * 6)) * v1,
          center + Math.sin(Math.PI / 43200 * (x1 - 3600 * 6)) * v1
        );
        ctx.lineTo(
          center + Math.cos(Math.PI / 43200 * (x2 - 3600 * 6)) * v2,
          center + Math.sin(Math.PI / 43200 * (x2 - 3600 * 6)) * v2
        );
        ctx.stroke();
      }
    } else {
      for (let i = 0; i < data.length - 1; i++) {
        const y1 = data[i].value;
        const y2 = data[i + 1].value;
        const v1 = (y1 - min) / (max - min) * (radius - radiusSmall) + radiusSmall;
        const v2 = (y2 - min) / (max - min) * (radius - radiusSmall) + radiusSmall;
        ctx.beginPath();
        ctx.lineTo(
          center + Math.cos(Math.PI * 2 / data.length * i - Math.PI / 2) * v1,
          center + Math.sin(Math.PI * 2 / data.length * i - Math.PI / 2) * v1
        );
        ctx.lineTo(
          center + Math.cos(Math.PI * 2 / data.length * (i+1) - Math.PI / 2) * v2,
          center + Math.sin(Math.PI * 2 / data.length * (i+1) - Math.PI / 2) * v2
        );
        ctx.stroke();
      }
    }
  }
  function draw_line_data(color, data) {
    ctx.lineWidth = 1.5;
    ctx.strokeStyle = color;
    for (let i = 0; i < data.length - 1; i++) {
      const date1 = new Date(data[i].created_at);
      const x1 = (date1.getTime() - x_start) / (x_end - x_start) * (width - 100);
      const y1 = data[i].value / (max - min) * (height - 100);
      const date2 = new Date(data[i + 1].created_at);
      const x2 = (date2.getTime() - x_start) / (x_end - x_start) * (width - 100);
      const y2 = data[i + 1].value / (max - min) * (height - 100);
      ctx.strokeStyle = color;
      ctx.beginPath();
      ctx.lineTo(x1 + 50, height - 50 - y1);
      ctx.lineTo(x2 + 50, height - 50 - y2);
      ctx.stroke();
    }
  }

  if (question.plot_type === 'line') {
    ctx.strokeStyle = '#c5c5c5';
    // x/y축 & y축 단위 출력

    ctx.textAlign = 'right';
    for (let i = 0; i < 6; i++) {
      const y = height - 50 - (i * (height - 100) / 5);
      ctx.beginPath();
      ctx.lineTo(50, y);
      ctx.lineTo(width - 50, y);
      ctx.stroke();
      ctx.fillText(Math.floor(i / 5 * (Math.abs(max - min)) + min), 45, y);
    }
    ctx.textAlign = 'center';
    if (num == 9 || num == 7) {
      for (let i = 0; i < 12; i++) {
        const x = 50 + (i * (width - 100) / (12 - 1));
        ctx.beginPath();
        ctx.lineTo(x, 50);
        ctx.lineTo(x, width - 50);
        ctx.stroke();
        ctx.save();
        ctx.fillText(`${i+1}월`, x, height - 35);
       }
    }
    else if (num == 3) {
      for (let i = 0; i < 6; i++) {
        const x = 50 + (i * (width - 100) / (6 - 1));
        const cur = new Date(x_start + i * (x_end - x_start) / (6 - 1));
        const day = cur.getDate();
        ctx.beginPath();
        ctx.lineTo(x, 50);
        ctx.lineTo(x, width - 50);
        ctx.stroke();
        ctx.save();
        ctx.fillText(`${day}일`, x, height - 35);
      }
    }
    else {
      for (let i = 0; i < 6; i++) {
        const x = 50 + (i * (width - 100) / (6 - 1));
        const cur = new Date(x_start + i * (x_end - x_start) / (6 - 1));
        const hours = `0${cur.getHours()}`.slice(-2);
        const minute = `0${cur.getMinutes()}`.slice(-2);
        ctx.beginPath();
        ctx.lineTo(x, 50);
        ctx.lineTo(x, width - 50);
        ctx.stroke();
        ctx.save();
        ctx.fillText(`${hours}:${minute}`, x, height - 35);
       }
    }
    draw_line_data('#ff7f00', question.data);
  } else if (question.plot_type === 'radial') {
    if (question.interval === 'day') {
      // 그리드 출력
      for (let i = 0; i < 24; i++) {
        if (i == 0) {
          ctx.strokeStyle = '#000000';
        } else if (i % 3 == 0) {
          ctx.strokeStyle = '#c5c5c5';
        } else {
          ctx.strokeStyle = '#f0f0f0';
        }
        ctx.beginPath();
        const x = Math.cos(Math.PI / 12 * (i - 6));
        const y = Math.sin(Math.PI / 12 * (i - 6));
        ctx.lineTo(center + x * radiusSmall, center + y * radiusSmall);
        ctx.lineTo(center + x * radius, center + y * radius);
        ctx.stroke();
        if (i == 0)
          if (center < 200)
            ctx.fillText(`${i} 시`, center + x * (radius + 8), center + y * (radius + 8));
          else
            ctx.fillText(`${i} 시`, center + x * (radius + 12), center + y * (radius + 12));
        else if (i % 3 == 0)
          if (center < 200)
            ctx.fillText(i, center + x * (radius + 8), center + y * (radius + 8));
          else
            ctx.fillText(i, center + x * (radius + 12), center + y * (radius + 12));
      }
    } else if (question.interval === 'week') {
      // 그리드 출력
      for (let i = 0; i < 53; i++) {
        if (i == 0) {
          ctx.strokeStyle = '#000000';
        } else if (i % 5 == 0) {
          ctx.strokeStyle = '#c5c5c5';
        } else {
          ctx.strokeStyle = '#f0f0f0';
        }
        ctx.beginPath();
        const x = Math.cos(Math.PI * 2 / 53 * i - Math.PI / 2);
        const y = Math.sin(Math.PI * 2 / 53 * i - Math.PI / 2);
        ctx.lineTo(center + x * radiusSmall, center + y * radiusSmall);
        ctx.lineTo(center + x * radius, center + y * radius);
        ctx.stroke();
        if (i == 0)
          if (center < 200)
            ctx.fillText(`${i + 1} 주`, center + x * (radius + 8), center + y * (radius + 8));
          else
            ctx.fillText(`${i + 1} 주`, center + x * (radius + 12), center + y * (radius + 12));
        else if (i % 5 == 0)
          if (center < 200)
            ctx.fillText(i, center + x * (radius + 8), center + y * (radius + 8));
          else
            ctx.fillText(i, center + x * (radius + 12), center + y * (radius + 12));
      }
    } else if (question.interval === 'month') {
      // 그리드 출력
      for (let i = 0; i < 12; i++) {
        if (i == 0) {
          ctx.strokeStyle = '#000000';
        } else if (i % 2 == 0) {
          ctx.strokeStyle = '#c5c5c5';
        } else {
          ctx.strokeStyle = '#f0f0f0';
        }
        ctx.beginPath();
        const x = Math.cos(Math.PI * 2 / 12 * i - Math.PI / 2);
        const y = Math.sin(Math.PI * 2 / 12 * i - Math.PI / 2);
        ctx.lineTo(center + x * radiusSmall, center + y * radiusSmall);
        ctx.lineTo(center + x * radius, center + y * radius);
        ctx.stroke();
        if (i == 0)
          if (center < 200)
            ctx.fillText(`${i + 1} 월`, center + x * (radius + 8), center + y * (radius + 8));
          else
            ctx.fillText(`${i + 1} 월`, center + x * (radius + 12), center + y * (radius + 12));
        else if (i % 2 == 0)
          if (center < 200)
            ctx.fillText(i + 1, center + x * (radius + 8), center + y * (radius + 8));
          else
            ctx.fillText(i + 1, center + x * (radius + 12), center + y * (radius + 12));
      }
    }
    ctx.textAlign = 'right';
    ctx.textBaseline = 'middle';
    // x/y축 & y축 단위 출력
    for (let i = 0; i < 6; i++) {
      if (i == 0 || i == 5)
        ctx.strokeStyle = '#c5c5c5';
      else
        ctx.strokeStyle = '#f0f0f0';
      ctx.beginPath();
      ctx.arc(center, center, i * (radius - radiusSmall) / 5 + radiusSmall, 0, Math.PI * 2, true);
      ctx.stroke();
      ctx.strokeStyle = '#000000';
      ctx.beginPath();
      ctx.lineTo(center, -i * (radius - radiusSmall) / 5 - radiusSmall + center);
      ctx.lineTo(center - 5, -i * (radius - radiusSmall) / 5 - radiusSmall + center);
      ctx.stroke();
      ctx.fillText(Math.floor(i / 5 * (Math.abs(max - min)) + min), center - 10, center - radiusSmall - i * (radius - radiusSmall) / 5);
    }
    ctx.lineWidth = 1.5;
    draw_radial_data('#ff7f00', question.data);
  }
}
// draw_radial_plot();
// draw_radial_data('#ff7f00', seoul_2020.data);
// draw_radial_data('#ff4500', seoul_2021.data);
// draw_radial_data('#836fff', seoul_2022.data);
// draw_radial_data('#00ff7f', seoul_2023.data);
// draw_line_plot();
// draw_line_data('#ff7f00', awair_co2.data);

let start;

function load_question() {
  document.getElementById('question_text').innerText = `질문 ${num}. ${questions_meta[num].question}`;
  
  init_canvas(questions_meta[num]);
  console.log(questions_meta[num]);
  const radios = document.getElementsByName('answer');
  for (let i = 0; i < radios.length; i++) {
    document.getElementById(`answer_${i+1}`).value = questions_meta[num].answers[i].answerId;
    document.getElementById(`answer_${i+1}`).nextElementSibling.innerHTML = questions_meta[num].answers[i].answerText;
  }
  
  start = Date.now();
}

function put_answer() {
  const end = Date.now();
  let answer_num = null;

  const radios = document.getElementsByName('answer');
  for (let i = 0; i < radios.length; i++) {
    if (radios[i].checked) {
      radios[i].checked = false;
      answer_num = parseInt(radios[i].value);
      break;
    }
  }
  if (answer_num == null) {
    console.log('정답이 입력되지 않았습니다.');
  } else if (num == question_size) {
    answers.push({
      chartType: "radial",
      questionId: num,
      answerId: answer_num,
      timeTaken: end - start
    });
    console.log(answers);
    let xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
      if (this.readyState == 4 && this.status == 200) {
        location.href = '/complete.html';
      }
    };
    xhttp.open("POST", `/api/survey/save/${userId}`)
    xhttp.withCredentials = true;;
    xhttp.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
    xhttp.send(JSON.stringify(answers));
  }
  else if (num == 0) {
    num++;
    load_question();
  } 
  else {
    console.log((end - start) / 1000, answer_num);
    answers.push({
      chartType: questions_meta[num].plot_type,
      questionId: num,
      answerId: answer_num,
      timeTaken: end - start
    });
    num++;
    if (num == question_size) {
      document.querySelector('#answer_btn').value = '제출';
    }
    load_question();
  }
}