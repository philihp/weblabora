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
    if (node.hasAttribute("draggable") && (node.getAttribute("draggable") == "true")) {
      return node;
    }
  }
  return findDraggableAncestor(node.parentNode);
}

function onBuildingDragStart(event) {
  var draggableNode = findDraggableAncestor(event.target);
  event.dataTransfer.effectAllowed = 'move';
  event.dataTransfer.setData('Text', draggableNode.id);
  //event.dataTransfer.addElement(draggableNode);
  //event.dataTransfer.setDragImage(draggableNode, 0, 0);
}

function onBuildingDrop(event) {
  var data = event.dataTransfer.getData('Text');

  event.target.appendChild(document.getElementById(data));
  event.stopPropagation();
  event.preventDefault();
}

function onBuildingDragOver(event) {
  event.preventDefault();
  return false;
}

function onBuildingDragEnd(event) {
  //event.target.parentNode.removeChild(event.target);
  event.preventDefault();
  return false;
}
