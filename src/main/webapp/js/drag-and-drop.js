/**
 * Returns {@code draggable} {@code HTMLElement} containing specified node.
 * 
 * This function allows to deal with incompatibilities among browsers on what
 * is actually being dragged. Some browsers select inner {@code a} element while
 * others inner {@code img} element. The function allows to get to the ancestor
 * element that was actually supposed to be dragged (has {@code draggable}
 * attribute set to {@code true}).
 * 
 * Keep in mind the HTML5 standard states that {@code img} and {@code a}
 * elements have the {@code draggable} attribute set to {@code true} by default.
 * 
 * @param node Node from which lookup will start.
 * @returns {@code node} if it is an {@code HTMLElement} with {@code draggable}
 *          attribute set to {@code true}. Otherwise if it has an ancestor
 *          {@code HTMLElement} with {@code draggable} attribute set to
 *          {@code true} then deepest (first when going up from {@code node})
 *          such element. Otherwise (which also includes a case when
 *          {@code true} is {@code null}) {@code null} is returned.
 * @returns This is always an {@code HTMLElement} object or {@code null}.
 */
function findDraggableAncestor(node) {
  if (node === null) {
    return null;
  }
  if (node instanceof HTMLElement) {
    if (node.hasAttribute('draggable') && (node.getAttribute('draggable') === 'true')) {
      return node;
    }
  }
  return findDraggableAncestor(node.parentNode);
}

/**
 * Determines whether specified element has specified class.
 * 
 * @param element Element which {@code class} attribute will be checked.
 * @param cls Class looked for in {@code element}.
 * @returns {@code true} if {@code element} is not {@code null}, is of
 *          {@code HTMLElement} type and its {@code class} attribute contains
 *          {@code cls} class; {@code false} otherwise.
 * @todo Once {@code classList} in HTML5 gains wider support consider using it
 *       instead.
 */
function hasClass(element, cls) {
  if (element === null) {
    return false;
  }
  if (element instanceof HTMLElement) {
    return element.className.search('(^|\\s)' + cls + '(\\s|$)') != -1;
  }
  return false;
}

/**
 * Returns player board element containing specified node.
 * 
 * Player boards are contained in {@code div} elements with {@code board}
 * {@code class} (among other classes). This function returns such a board
 * element being an ancestor of the specified node.
 * 
 * @param node Node which player board element is to be found.
 * @returns Player board element containing {@code node} or {@code null} if
 *          {@code node} is neither a player board element nor a descendant of
 *          one.
 * @returns This is always an {@code HTMLElement} object or {@code null}.
 */
function findBoard(node) {
  if (node === null) {
    return null;
  }
  if (node instanceof HTMLElement) {
    if (hasClass(node, 'board')) {
      return node;
    }
  }
  return findBoard(node.parentNode);
}

/**
 * Returns number of resources of specified type a player has available without
 * doing any conversions.
 * 
 * The function is not "smart" in that it does not include convertible
 * resources. If you ask for {@code Straw} {@code Grain} will not be counted in
 * even thou a player may convert {@code Grain} to {@code Straw} at any time.
 * Also if you ask for {@code Penny} no {@code Nickel}s will be counted.
 * 
 * @param board {@code div} element with {@code board} {@code class} being the
 *              player's board. This identifies player.
 * @param resource Name of the resource asked for. For example "{@code Wood}".
 * @returns Number of resources of the {@code resource} type the player has
 *          available without doing any conversions.
 * @returns This will always be at least {@code 0}.
 */
function getAvailableResources(board, resource) {
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

/**
 * Returns number of resources of specified type a player has available
 * including possibility of doing conversions.
 * 
 * The function is "smart" in that it does include convertible resources. If you
 * ask for {@code Straw} {@code Grain} will be counted in as well. Also if you
 * ask for {@code Penny} {@code Nickel}s, {@code Wine} and {@code Beer} will be
 * counted as well.
 * 
 * @param board {@code div} element with {@code board} {@code class} being the
 *              player's board. This identifies player.
 * @param resource Name of the resource asked for. For example "{@code Wood}".
 * @returns Number of resources of the {@code resource} type the player has
 *          available including possibility of doing conversions.
 * @returns This will always be at least {@code 0}.
 */
function getAvailableResourcesWithConversions(board, resource) {
  // First get the available resources of the asked type without any
  // conversions.
  var baseCount = getAvailableResources(board, resource);

  // Now include convertible resources.
  var convertedCount = 0;

  if (resource === 'Straw') {
    convertedCount = getAvailableResources(board, 'Grain');
  }
  else if (resource === 'Penny') {
    convertedCount = getAvailableResources(board, 'Nickel') * 5;
    convertedCount += getAvailableResources(board, 'Wine');
    convertedCount += getAvailableResources(board, 'Beer') * 2;
  }
  else if (resource === 'Nickel') {
    var pennies = getAvailableResources(board, 'Penny');
    pennies += getAvailableResources(board, 'Wine');
    pennies += getAvailableResources(board, 'Beer') * 2;
    convertedCount = pennies / 5;
  }

  return baseCount + convertedCount;
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
  var strawAvailable = getAvailableResources(board, 'Straw');
  var strawNeeded = droppedBuilding.getAttribute('data-cost-straw');

  var dropReplacementsList = event.target.getElementsByClassName('drop-replacement');
  for (var i = 0; i < dropReplacementsList.length; ++i) {
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
  var allowDrop = true;

  // Player board element over which the drag is.
  var board = findBoard(event.target);

  // Check whether the drag is over active player board.

  if (!hasClass(board, "board--active")) {
    allowDrop = false;
  } else {
    // Cell over which the drag is.
    var cell = event.target;

    // Keep in mind that cells that are not part of the board landscape will
    // never get here since they do not handle d'n'd and thus preemptively
    // reject any drag.

    // Check whether the cell over which the drag is is empty.

    if (!cell.hasAttribute('data-is-empty') || (cell.getAttribute('data-is-empty') !== 'true')) {
      allowDrop = false;
    } else {
      // Dragged building.
      var dragData = event.dataTransfer.getData('Text');
      var dragBuilding = document.getElementById(dragData);

      // Terrain types the dragged building can be erected on.
      var buildingTerrainTypes = dragBuilding.getAttribute('data-terrain-types');
      // Terrain type of the cell over which the drag is.
      var cellTerrainType = cell.getAttribute('data-terrain-type');

      // Check whether the cell over which the drag is is of proper terrain
      // type.

      if (buildingTerrainTypes.indexOf(cellTerrainType, 0) == -1) {
        allowDrop = false;
      } else {
        // Determines whether dragged building is a cloister building.
        var isCloister = (dragBuilding.hasAttribute('data-is-cloister') && (dragBuilding.getAttribute('data-is-cloister') === 'true'));
        // Determines whether the cell over which the drag is has a cloister
        // neighbor.
        var isCellACloisterNeighbor = (cell.hasAttribute('data-has-cloister-neighbor') && (cell.getAttribute('data-has-cloister-neighbor') === 'true'));

        if (isCloister && !isCellACloisterNeighbor) {
          allowDrop = false;
        } else {
          // Check available resources.

          var hasEnoughResources = true;
          // Instead of checking for ahead-known resources used for building
          // enumerate all the attributes looking for ones named data-cost-*
          // (where * is the resource type). This way the code is independent
          // from types of resources used for building.
          for (var i = 0; i < dragBuilding.attributes.length; ++i) {
            var attributeNode = dragBuilding.attributes[i];
            var result = attributeNode.nodeName.match('^data-cost-(.*)$');
            if (result === null) {
              continue;
            }
            // else the name matched and result[1] is the lower-case resource
            // name.
            var resourceName = result[1];
            // Normalize the name.
            resourceName = resourceName.charAt(0).toUpperCase() + resourceName.slice(1);

            var availableResources = getAvailableResourcesWithConversions(board, resourceName);
            var requiredResources = attributeNode.nodeValue;

            if (availableResources < requiredResources) {
              allowDrop = false;
              hasEnoughResources = false;
              break;
            }
          }

          if (hasEnoughResources) {
            // TODO: Still need to check for possible second drop.
          }
        }
      }
    }
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
