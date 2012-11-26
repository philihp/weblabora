/** Returns draggable HTMLElement containing specified node.
 * @param node Node from which lookup will start.
 * @returns node if node is an HTMLElement with draggable attribute set to true.
 *          Otherwise if node has an ancestor HTMLElement with draggable
 *          attribute set to true then deepest (first when going up from node)
 *          such element. Otherwise (which also includes a case when node is
 *          null) null is returned.
 * 
 * This function allows to deal with incompatibilities among browsers on what
 * is actually being dragged. Some browsers select inner A element while others
 * inner IMG element. The function allows to get to the ancestor element that
 * was actually supposed to be dragged (has draggable attribute set to true).
 * 
 * Keep in mind the HTML5 standard states that IMG and A elements have the
 * draggable attribute set to true by default.
 */
function findDraggableAncestor(node) {
  if (node == null) {
    return null;
  }
  if (node instanceof HTMLElement) {
    if (node.hasAttribute('draggable') && (node.getAttribute('draggable') === 'true')) {
      return node;
    }
  }
  return findDraggableAncestor(node.parentNode);
}

function findBoard(node) {
  if (node == null) {
    return null;
  }
  if (node instanceof HTMLElement) {
    var classList = node.className.split(/\s+/);
    for (var i = 0; i < classList.length; ++i) {
      if (classList[i] === 'board') {
        return node;
      }
    }
  }
  return findBoard(node.parentNode);
}

function countResources(board, resource) {
  var resources = board.getElementsByClassName('resources')[0];
  var resourceList = resources.getElementsByClassName('resource');
  var count = 0;
  for (var i = 0; i < resourceList.length; ++i) {
    if (resourceList[i].getAttribute('data-resource') === resource) {
      ++count;
    }
  }
  return count;
}

function onBuildingDragStart(event) {
  var draggableNode = findDraggableAncestor(event.target);
  event.dataTransfer.effectAllowed = 'move';
  event.dataTransfer.setData('Text', draggableNode.id);
}

function onBuildingDrop(event) {
  var data = event.dataTransfer.getData('Text');

  var droppedBuilding = document.getElementById(data);

  event.target.appendChild(droppedBuilding);
  event.stopPropagation();
  event.preventDefault();

  var board = findBoard(event.target);
  var strawAvailable = countResources(board, 'Straw');
  var strawNeeded = droppedBuilding.getAttribute('data-cost-straw');

  var dropReplacementsList = event.target.getElementsByClassName('drop-replacement');
  for(var i = 0; i < dropReplacementsList.length; ++i) {
    var dropReplacement = dropReplacementsList.item(i);
    dropReplacement.style.display = 'none';
  }

  var buildingId = data.match('^building-(.*)$')[1];
  var row = event.target.getAttribute('data-position-row');
  var column = event.target.getAttribute('data-position-column');

  var command = '';

  for (var i = strawAvailable; i < strawNeeded; ++i) {
    if (command.length > 0 ) {
      command += '|';
    }
    command += 'V(Gn)';
  }

  if (command.length > 0 ) {
      command += '|';
  }
  command += 'B(' + buildingId + ',' + column + ',' + row + ')';

  var moveForm = document.forms['moveForm'];
  moveForm.token.value = command;
}

function onBuildingDragOver(event) {
  var data = event.dataTransfer.getData('Text');

  var allowDrop = true;

  var droppedBuilding = document.getElementById(data);
  var allowedTerrainTypes = droppedBuilding.getAttribute('data-terrain-types');
  var currentTerrainType = event.target.getAttribute('data-terrain-type');

  if (allowedTerrainTypes.indexOf(currentTerrainType, 0) == -1) {
    allowDrop = false;
  }

  if (allowDrop) {
    event.preventDefault();
  }

  return true;
}

function onBuildingDragEnd(event) {
  event.preventDefault();
  return false;
}
