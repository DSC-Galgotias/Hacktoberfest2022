const input = document.getElementById('input-text');
const addTaskButton = document.getElementById('add-task');
const list = document.getElementById('tasks-list');

list.addEventListener('click', deleteOrComplete)

addTaskButton.addEventListener('click', createTask);

function createTask(){
    let inputText = input.value;
    if(!inputText) return;
    let newTask = document.createElement('div')
    newTask.setAttribute('class', 'task')
    newTask.innerHTML = `<li>${inputText}</li>`

    //create button
    let completedBtn = document.createElement('button')
    completedBtn.innerText = 'Done'
    completedBtn.setAttribute('class', 'btn done')
    newTask.appendChild(completedBtn)

    // delete button
    let deleteBtn = document.createElement('button')
    deleteBtn.innerText = 'Remove'
    deleteBtn.setAttribute('class', 'btn delete')
    newTask.appendChild(deleteBtn)

    list.append(newTask) 
    
    input.value = ''
}

function deleteOrComplete(e) {
    const item = e.target

    if(item.classList[1] === 'done'){
        let todo = item.parentElement.children[0]
        todo.classList.toggle('completed')
    }

    if(item.classList[1] === 'delete'){
        item.parentElement.remove()
    }
}