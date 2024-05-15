const getCurrentOffer = () => {
  return fetch("/api/current-offer")
      .then(response => response.json());
}

const refreshOffer = (offer) => {
  const offerTotalEl = document.querySelector("#offerTotal");
  const offerItemsCountEl = document.querySelector("#offerItemsCount");

  offerTotalEl.textContent = offer.total;
  offerItemsCount.textContent = offer.itemsCount;
}

const getProducts=()=> {
  return fetch("/api/products")
      .then(response => response.json());
}

const createProductHtmlEl = (productData) => {
  const template = `
        <div class="produkt">
            <h4 class="tekstTlo">${productData.name}</h4>
            <img src="https://gamehag.com/img/rewards/logo/250%20smoczych%20monet.png" height = 200 />
            <span class="tekstTlo">${productData.price}</span>
            <button data-id="${productData.price}">Add to cart </button>
        </div>
        `;

  const htmlEl = document.createElement("li");
  htmlEl.innerHTML = template.trim();
  return htmlEl;
}


document.addEventListener("DOMContentLoaded", ()=> {
  const productsListEl = document.querySelector('#productsList');

  getProducts()
      .then(productsAsJson => productsAsJson.map(createProductHtmlEl))
      .then(productsAsHtml => {
        productsAsHtml.forEach(el => productsListEl.appendChild(el))
      });

  getCurrentOffer()
      .then(offer => refreshOffer(offer));
});