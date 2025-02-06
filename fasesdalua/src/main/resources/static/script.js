document.addEventListener("DOMContentLoaded", () => {
    gerarEstrelas(300);
    
    document.querySelector("form").addEventListener("submit", (event) => {
        event.preventDefault();
        buscarFase();
    });
});

function buscarFase() {
    const data = document.getElementById("data").value;
    if (!data) {
        alert("Por favor, selecione uma data.");
        return;
    }

    fetch(`/fase-lua?data=${data}`)
        .then(response => response.text())
        .then(fase => {
            document.getElementById("resultado").innerText = `A fase da Lua nessa data é: ${fase}`;
        })
        .catch(error => console.error("Erro:", error));
}

function gerarEstrelas(qtd = 300) {
    const starsContainer = document.querySelector(".stars");
    if (!starsContainer) {
        console.error("Elemento .stars não encontrado!");
        return;
    }

    starsContainer.innerHTML = ""; 

    for (let i = 0; i < qtd; i++) {
        const star = document.createElement("div");
        star.classList.add("star");
        star.style.backgroundColor = "rgba(255, 255, 255, 0.9)";
    
      
        star.style.top = `${Math.random() * 100}%`;
        star.style.left = `${Math.random() * 100}%`;
        star.style.animationDelay = `${Math.random() * 2}s`;
        star.style.animationDuration = `${1 + Math.random() * 2}s`;
        star.style.animationTimingFunction = `cubic-bezier(${Math.random()}, ${Math.random()}, ${Math.random()}, ${Math.random()})`;

     
        starsContainer.appendChild(star);
    }
    
document.querySelectorAll('.star').forEach(star => {
    star.style.willChange = 'opacity, transform';
});
}
