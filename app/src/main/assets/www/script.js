// 页面加载完成后执行[9](@ref)
document.addEventListener('DOMContentLoaded', function() {
    // 动态内容示例
    const contentDiv = document.getElementById('content');
    contentDiv.innerHTML = '<p>页面已加载完成@@@@@@@@@@@@@！</p>';

    // 点击事件监听
    document.querySelectorAll('.nav-list a').forEach(link => {
        link.addEventListener('click', function(e) {
            e.preventDefault();
            console.log('导航点击:', this.href);
        });
    });
});

// 简单函数示例
function addListItem(text) {
    const ul = document.createElement('ul');
    const li = document.createElement('li');
    li.textContent = text;
    ul.appendChild(li);
    document.body.appendChild(ul);
}