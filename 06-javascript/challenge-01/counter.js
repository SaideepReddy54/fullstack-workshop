let count = 0;
let step = 1;

const countEl = document.getElementById('count');

const updateUI = () => {
  countEl.textContent = `${count}`;
  countEl.style.color =
    count > 0 ? 'green' :
    count < 0 ? 'red' : 'black';
};

document.getElementById('increment').onclick = () => {
  count += step;
  updateUI();
};

document.getElementById('decrement').onclick = () => {
  count = Math.max(0, count - step);
  updateUI();
};

document.getElementById('reset').onclick = () => {
  count = 0;
  updateUI();
};

document.querySelectorAll('[data-step]').forEach(button => {
  button.onclick = () => {
    step = Number(button.dataset.step);
  };
});
