import styled from 'styled-components';

const color = {
    black: {
        default: {
            background: "black",
            text: "#cbcbcb",
            outline: "#e9e8e8",
        },

        hovered: {
            background: "#3b3b3b",
        },
    },
    white: {
        default: {
            background: "white",
            text: "black",
            outline: "black",
        },

        hovered: {
            background: "#c9c9c9",
        },
    },
    purple: {
        default: {
            background: "#211126",
            text: "#6b307e",
            outline: "white",
        },

        hovered: {
            background: "#180c1c",
        },
    },
};

export const TextButton = styled.text`
  color: ${(properties) => color[properties.color].default.background};
  outline-color: ${(properties) => color[properties.color].default.outline};

  border: 0;
  cursor: pointer;
  transition: ease background-color 170ms;
  font-family: "Segoe UI Black";
  
  &:hover {
    color: ${(properties) => color[properties.color].hovered.background};
    outline-color: ${(properties) => color[properties.color].hovered.outline};
  }
  &:disabled {
    cursor: default;
    opacity: 0.7;
  }
`;

export const Button = styled.button`
  
`;