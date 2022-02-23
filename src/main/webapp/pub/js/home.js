const productContainers = [...document.querySelectorAll('.product-container')];

const nextBtn = [...document.querySelectorAll('.nxt-btn')];

const prevBtn = [...document.querySelectorAll(".pre-btn")];

productContainers.forEach((item,i)=>{
    let containerDimensions = item.getBoundingClientRect();
    let containerWidth = containerDimensions.width;

    nextBtn[i].addEventListener("click", () =>{
        item.scrollLeft += containerWidth;
    });

    prevBtn[i].addEventListener("click", () => {
      item.scrollLeft -= containerWidth;
    });

});
