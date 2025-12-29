const tabsData = [
    {
        title: 'Overview',
        content: 'This section gives a brief overview of the product. It explains what the product is, who it is for, and the main purpose it serves.'
    },
    {
        title: 'Features',
        content: 'This section lists the key features of the product such as ease of use, fast performance, secure data handling, and user-friendly design.'
    },
    {
        title: 'Pricing',
        content: 'This section explains the pricing plans available. Users can choose between free, basic, or premium plans depending on their needs.'
    }
];


const tabsContainer = document.getElementById("tabs");
const contentBox = document.getElementById("content");

let activeIndex = 0;
tabsData.forEach((tab, index) => {
    const tabBtn = document.createElement("div");
    tabBtn.textContent = tab.title;
    tabBtn.className = "tab";
    tabBtn.tabIndex = 0; 

    tabBtn.onclick = () => activateTab(index);

    tabBtn.onkeydown = (e) => {
        if (e.key === "ArrowRight") {
            activateTab((index + 1) % tabsData.length);
        }
        if (e.key === "ArrowLeft") {
            activateTab((index - 1 + tabsData.length) % tabsData.length);
        }
    };
    tabsContainer.appendChild(tabBtn);
});
function activateTab(index) {
    const allTabs = document.querySelectorAll(".tab");

    allTabs.forEach(tab => tab.classList.remove("active"));

    allTabs[index].classList.add("active");
    contentBox.textContent = tabsData[index].content;

    activeIndex = index;
}
activateTab(0);
