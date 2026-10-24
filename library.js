
document.addEventListener('DOMContentLoaded', () => {
    const search = document.getElementById('search');
    const body = document.getElementById('rows');
    const prev = document.getElementById('prev');
    const next = document.getElementById('next');
    const page = document.getElementById('page');

    let current = 1;
    let size = 5;
    let filtered = [];

    function render() {
        const rows = [...body.querySelectorAll('.data-row')];
        const q = search.value.toLowerCase().trim();

        filtered = rows.filter(r =>
            r.innerText.toLowerCase().includes(q)
        );

        rows.forEach(r => {
            r.style.display = 'none';
        });

        const pages = Math.max(
            1,
            Math.ceil(filtered.length / size)
        );

        current = Math.min(current, pages);

        filtered
            .slice((current - 1) * size, current * size)
            .forEach(r => {
                r.style.display = '';
            });

        let empty = document.getElementById('client-empty');

        if (!empty) {
            empty = document.createElement('p');
            empty.id = 'client-empty';
            empty.className = 'empty';
            empty.textContent = 'No records found';

            body.parentElement.appendChild(empty);
        }

        empty.style.display = filtered.length ? 'none' : '';

        page.textContent = `Page ${current} of ${pages}`;

        prev.disabled = current === 1;
        next.disabled = current === pages;
    }

    search.addEventListener('input', () => {
        current = 1;
        render();
    });

    prev.addEventListener('click', () => {
        if (current > 1) {
            current--;
            render();
        }
    });

    next.addEventListener('click', () => {
        if (current < Math.ceil(filtered.length / size)) {
            current++;
            render();
        }
    });

    render();
});
