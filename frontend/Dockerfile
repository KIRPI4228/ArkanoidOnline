FROM node:20.4.0-alpine as stage1
WORKDIR /frontend
COPY package.json ./
RUN npm install

FROM node:20.4.0-alpine as stage2
WORKDIR /frontend
ENV GENERATE_SOURCEMAP false
COPY src ./src
COPY public ./public
COPY .env.production ./.env.production
COPY --from=stage1 /frontend ./
RUN npm run build

FROM node:20.4.0-alpine as stage3
WORKDIR /frontend
ENV NODE_ENV production
COPY --from=stage2 /frontend/build ./build
RUN npm install -g serve

EXPOSE 80

CMD [ "serve", "-l", "80", "-s", "build" ]
